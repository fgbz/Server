package phalaenopsis.fgbz.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.fgbz.dao.ILog;
import phalaenopsis.fgbz.dao.LawstandardDao;
import phalaenopsis.fgbz.dao.SystemDao;
import phalaenopsis.fgbz.entity.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static phalaenopsis.common.method.Basis.getCurrentFGUser;

@Aspect
@Component
public class FgbzLogService {
    @Autowired
    private LawstandardService lawstandardService;

    @Autowired
    private LawstandardDao lawstandardDao;

    @Autowired
    private SystemDao systemDao;

    public List<LawstandardType> upids =new ArrayList<>();

    //保存编辑法规
    @Pointcut("execution(* phalaenopsis.fgbz.service.LawstandardService.SaveOrUpdateLawstandard(..))")
    public void  changeLawTypeCount(){
    }
    //删除法规
    @Pointcut("execution(* phalaenopsis.fgbz.service.LawstandardService.DeleteLawstandardById(..))")
    public void  changeLawTypeCountDelete(){
    }

    //审核法规
    @Pointcut("execution(* phalaenopsis.fgbz.service.UserCenterService.SaveApprove(..))")
    public void  changeLawTypeCountExam(){
    }

    //保存日志
    @Pointcut("execution(* phalaenopsis.fgbz.service.*.*(..))"+"||execution(* phalaenopsis.common.service.AttachmentService.*(..))")
    public void  SaveLog(){
    }
    //保存登陆日志
    @Pointcut("execution(* phalaenopsis.fgbz.service.SystemServie.login(..))")
    public void  SaveLoginLog(){
    }

   //保存法规时,记录solr表
    @Pointcut("execution(* phalaenopsis.fgbz.service.LawstandardService.SaveOrUpdateLawstandard(..))")
    public void SaveLawSolr(){
    }

    //修改状态时维护索引
    @Pointcut("execution(* phalaenopsis.fgbz.service.FgbzDicService.SaveOrUpdateLawstandardStatus(..))")
    public void SaveLawSolrStatusChange(){
    }

    //修改发布部门时维护索引
    @Pointcut("execution(* phalaenopsis.fgbz.service.FgbzDicService.SaveOrUpdatePublishdep(..))")
    public void SaveLawSolrPublishdepChange(){
    }

    //修改法规类别维护索引
    @Pointcut("execution(* phalaenopsis.fgbz.service.LawstandardService.AddOrUpdateLawstandardType(..))")
    public void SaveLawSolrLawtypeChange(){
    }


    @Around( "changeLawTypeCount()")
    public int  saveLawTypeCount(ProceedingJoinPoint point) throws Throwable {

        int aroundResult =0;
        Object[] args=point.getArgs();
        Lawstandard lawRequest = (Lawstandard) args[0];

        String requestId = lawRequest.getId();
        //传入的类别
        String requestlawtype = lawRequest.getLawtype();

        Lawstandard lawActual = new Lawstandard();

        if(!StrUtil.isNullOrEmpty(lawRequest.getId())){
            //数据库中的值
            lawActual = lawstandardService.getLawstandardById(lawRequest.getId());
        }

        int result = (int)point.proceed();
        aroundResult = result;

        //方法成功执行
        if(result== OpResult.Success){
            if(lawRequest.getApprovestatus()==3){
                //新增，发布
                if(StrUtil.isNullOrEmpty(requestId)){

                    List<LawstandardType> list = getLawtypeList(lawRequest.getLawtype());
                    lawstandardService.changeLawstandardCount(list,"add");
                }else{

                    //新发布
                    if(lawActual.getApprovestatus()!=3){
                        //变化后的加一
                        lawstandardService.changeLawstandardCount(getLawtypeList(lawActual.getLawtype()),"add");
                    }else{
                        if(!lawActual.getLawtype().equals(lawRequest.getLawtype())){
                            //原来的减一
                            lawstandardService.changeLawstandardCount(getLawtypeList(lawActual.getLawtype()),"delete");
                            //变化后的加一
                            lawstandardService.changeLawstandardCount(getLawtypeList(requestlawtype),"add");
                        }
                    }

                }
            }
        }
        return aroundResult;
    }

    @Around("changeLawTypeCountDelete()")
    public int  changeLawTypeCountDelete(ProceedingJoinPoint jp) throws Throwable {
        int aroundResult =0;
        Object[] args=jp.getArgs();
        String id = (String) args[0];
        Lawstandard lawActual = lawstandardService.getLawstandardById(id);

        int result = (int)jp.proceed();
        aroundResult = result;

        if(result== OpResult.Success){
            lawstandardService.changeLawstandardCount(getLawtypeList(lawActual.getLawtype()),"delete");
        }
        return aroundResult;
    }

    @AfterReturning(pointcut = "changeLawTypeCountExam()", returning = "returnValue")
    public void changeLawTypeCountExam(JoinPoint point,Object returnValue) {
        Object[] args=point.getArgs();
        int result = (int)returnValue;
        LawstandardApprove lawstandardApprove= (LawstandardApprove) args[0];

        if(result== OpResult.Success&&lawstandardApprove.getStatus()==1){
            Lawstandard lawActual = lawstandardService.getLawstandardById(lawstandardApprove.getLawstandardID());
            lawstandardService.changeLawstandardCount(getLawtypeList(lawActual.getLawtype()),"add");

            //维护索引
            lawstandardDao.DeleteSolrTextById(lawActual.getId());
            lawstandardDao.SaveSolrTextById(lawActual.getId());
        }
    }

    @AfterReturning(pointcut = "SaveLoginLog()", returning = "returnValue")
    public void SaveLoginLog(JoinPoint point,Object returnValue){
        Map<String, Object> map = (Map<String, Object>) returnValue;

        if(map!=null){
            if(map.get("LoginState")!=null&&map.get("LoginState").equals("Success")){
                FG_User user = (FG_User) map.get("LoginResult");
                //日志对象
                Fg_Log log = new Fg_Log();
                log.setOperationname("登录");
                log.setUserid(user.getId());
                log.setUsername(user.getUserrealname());
                log.setOrganizationid(user.getOrgid());
                log.setOrganizationname(user.getOrgname());

                systemDao.SaveFgLog(log);
            }
        }
    }

    //保存法规时维护索引
    @AfterReturning(pointcut = "SaveLawSolr()", returning = "returnValue")
    public void SaveLawSolr(JoinPoint point,Object returnValue){
        int result = (int)returnValue;

        Object[] args=point.getArgs();
        Lawstandard lawstandard = (Lawstandard) args[0];

        try{
            if(result==OpResult.Success){
                //发布时维护索引
                if(!StrUtil.isNullOrEmpty(lawstandard.getId())&&lawstandard.getApprovestatus()==3){
                    lawstandardDao.DeleteSolrTextById(lawstandard.getId());
                    lawstandardDao.SaveSolrTextById(lawstandard.getId());
                }
            }
        }catch(Exception e){

        }

    }

    //修改状态时维护索引
    @AfterReturning(pointcut = "SaveLawSolrStatusChange()", returning = "returnValue")
    public void SaveLawSolrStatusChange(JoinPoint point,Object returnValue){
        int result = (int)returnValue;

        Object[] args=point.getArgs();
        LawstandardStatus lawstandardStatus = (LawstandardStatus) args[0];

        if(result==OpResult.Success){
            //发布时维护索引
            if(!StrUtil.isNullOrEmpty(lawstandardStatus.getId())){
                List<Lawstandard> list = lawstandardDao.getLawsByStaus(lawstandardStatus.getId());
                HandleSolr(list);
            }
        }
    }
    //修改发布部门时维护索引
    @AfterReturning(pointcut = "SaveLawSolrPublishdepChange()", returning = "returnValue")
    public void SaveLawSolrPublishdepChange(JoinPoint point,Object returnValue){
        int result = (int)returnValue;

        Object[] args=point.getArgs();
        Publishdep publishdep = (Publishdep) args[0];

        if(result==OpResult.Success){
            //发布时维护索引
            if(!StrUtil.isNullOrEmpty(publishdep.getId())){
                List<Lawstandard> list = lawstandardDao.getLawsByPubdep(publishdep.getId());
                HandleSolr(list);
            }
        }
    }

    //修改法规类别维护索引
    @AfterReturning(pointcut = "SaveLawSolrLawtypeChange()", returning = "returnValue")
    public void SaveLawSolrLawtypeChange(JoinPoint point,Object returnValue){
        int result = (int)returnValue;

        Object[] args=point.getArgs();
        LawstandardType lawstandardType = (LawstandardType) args[0];

        if(result==OpResult.Success){
            //发布时维护索引
            if(!StrUtil.isNullOrEmpty(lawstandardType.getId())){
                List<Lawstandard> list = lawstandardDao.getLawsByLawType(lawstandardType.getId());
                HandleSolr(list);
            }
        }
    }

    //处理索引表
    public void HandleSolr( List<Lawstandard> list){
        if(list!=null&&list.size()>0){
            for (Lawstandard lawstandard:list
                    ) {
                lawstandardDao.DeleteSolrTextById(lawstandard.getId());
                lawstandardDao.SaveSolrTextById(lawstandard.getId());
            }
        }
    }
    /**
     * 获取类别树
     * @return
     */
    public List<LawstandardType> getLawtypeList(String id){
        upids =  new ArrayList<>();
        LawstandardType lawstandardType = new LawstandardType();
        lawstandardType.setId(id);
        upids.add(lawstandardType);
        getLawsTreeUp(id);
        return  upids;
    }


    /**
     * 向上递归树
     * @return
     */
    public LawstandardType getLawsTreeUp(String id){

        LawstandardType lawstandardType = lawstandardService.getParentLawstandardTypeById(id);

        while (lawstandardType!=null){
            upids.add(lawstandardType);
            lawstandardType=lawstandardService.getParentLawstandardTypeById(lawstandardType.getId());
        }
        return lawstandardType;
    }


    /***************************************************************/
    @After( "SaveLog()")
    public void  SaveFgLog(JoinPoint point){

        String comment = getServiceMthodDescription(point);
        if(StrUtil.isNullOrEmpty(comment)){
            return;
        }

        Object[] args=point.getArgs();
        FG_User user = getCurrentFGUser();

        if(user!=null){
            //日志对象
            Fg_Log log = new Fg_Log();
            log.setOperationname(comment);
            log.setUserid(user.getId());
            log.setUsername(user.getUserrealname());
            log.setOrganizationid(user.getOrgid());
            log.setOrganizationname(user.getOrgname());

            systemDao.SaveFgLog(log);
        }

    }

    /**
     * 查询方法描述
     * @param joinPoint
     * @return
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint) {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass;
        String description = "";
        try {
            targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();

            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        if(method.getAnnotation(ILog.class)!=null){
                            description = method.getAnnotation(ILog.class).description();
                        }
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        return description;
    }

}
