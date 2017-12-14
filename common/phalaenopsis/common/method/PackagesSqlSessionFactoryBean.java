package phalaenopsis.common.method;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import phalaenopsis.common.method.Tools.StrUtil;

public class PackagesSqlSessionFactoryBean extends SqlSessionFactoryBean {

	static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	
	private static int count = 0;

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(PackagesSqlSessionFactoryBean.class);

	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		
		ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
		typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/" + DEFAULT_RESOURCE_PATTERN;

		// 将加载多个绝对匹配的所有Resource
		// 将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分
		// 然后进行遍历模式匹配
		try {
			Set<String> result = new HashSet<String>();
			Resource[] resources = resolver.getResources(typeAliasesPackage);
			if (null != resources && resources.length != 0) {
				MetadataReader metadataReader = null;
				for (Resource resource : resources) {
					if (resource.isReadable()) {
						metadataReader = metadataReaderFactory.getMetadataReader(resource);
						try {
							result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage()
									.getName());
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}

			if (result.size() > 0) {
				List<String> list = new ArrayList<String>(result);
				super.setTypeAliasesPackage(StrUtil.join(",", list));  
			}
			else{
				logger.warn("参数typeAliasesPackage:"+typeAliasesPackage+"，未找到任何包");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
