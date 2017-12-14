<html>
<body onload="time()">
	<form action="/patrol/trackService/insertTrackMonitoring" method="post">
<table>
<thead>
<tr>
<th>First Name</th>
<th>Last Name</th>
</tr>
</thead>
<tfoot>
<tr>
<td colspan="2"><input type="submit" value="Save" /></td>
</tr>
</tfoot>
<tbody>
<tr>
<!--  <td><input name="patrolUserId" value="e9d0af70-6aa7-4108-8a08-ee4e5909ea68" /></td>
<td><input name="pageNo" value=1 /></td>
<td><input name="pageSize" value=10 /></td>
<td><input name="tempStartDate" value='2017-9-28 09:15:00' /></td>
<td><input name="tempEndDate" value='2017-9-28 18:15:00' /></td>  -->
<!-- <tr>
 <td><input name="patrolUserId" value="b28057a5-03c4-44aa-9dd2-586476d79113" /></td>
<td><input name="startTime" value="2017-10-8 10:10:10" /></td>
<td><input name="endTime" value="2017-10-18 10:10:10" /></td>
</tr> -->
 <tr>
 <td><input name="patrolList[0].patrolUserId" value="e9d0af70-6aa7-4108-8a08-ee4e5909ea68" /></td>
<td><input name="patrolList[0].xpos" value=120.45594367 /></td>
<td><input name="patrolList[0].ypos" value=36.34847771 /></td>
<td><input name="patrolList[0].patrolOrgId" value="664348f2-cedb-4d61-8f84-2bd877e97440" /></td>
<td><input name="patrolList[0].patrolDirection" value=-118.783799 /></td>
<td><input name="patrolList[0].tempPatrolTime" value="2017-09-12 15:33:11.979" /></td> 
<td><input name="patrolList[0].flag" value=true /></td>
</tr>
<tr>
<input name="patrolUserId" value="e9d0af70-6aa7-4108-8a08-ee4e5909ea68" />
</tr> 

</tbody>
</table>
</form>
</body>
</html>
