var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?rid=");
var rid = argsIndex[1];

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"MentalTest/QuerySingleRecord",
			{
				record_id:rid
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data.result);
				//Sconsole.log(data.result.RecordInfo.RecordInfo);
				
				//处理病人信息
				var patient = data.result.PatientInfo;
				var patientinfo1 = "姓名 ：" + patient.PatientName +"　　　　身份证号码：" + patient.PatientIndentity + "　　　　住院号/门诊号：" + patient.PatientNumber
				var patientinfo2 = "性别 ：" + patient.PatientGender + "　　　　民族 ：" + patient.PatientNation+"　　　　出生年月：" + patient.PatientBirthday + "　　　　住址：" + patient.PatientPlace;;
				$("#patientinfo1").append(patientinfo1);
				$("#patientinfo2").append(patientinfo2);
				
				//处理因子信息
				var factorinfo = "";
				for(var i = 0; i < data.result.RecordInfo.RecordFactor.length; i ++){
					factorinfo += "<div class=layui-col-xs3>"
					factorinfo += "因子名称 ：" + data.result.RecordInfo.RecordFactor[i].FactorName; 
					factorinfo += "</div>"
					factorinfo += "<div class=layui-col-xs3>"
					factorinfo += "因子得分 ：" + data.result.RecordInfo.RecordFactor[i].FactorScore;
					factorinfo += "</div>"
					
				}
				$("#factorinfo").append(factorinfo);
				
				/*
				 *     <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
						      移动：6/12 | 平板：6/12 | 桌面：4/12
						</div>
				 * */
				
				//处理参考信息
				var suggestinfo = "";
				for(var i = 0; i < data.result.RecordInfo.RecordInfo.length; i ++){
					suggestinfo += "【★因子名称 ：】" + data.result.RecordInfo.RecordFactor[i].FactorName + "</br>"; 
					suggestinfo += "【★因子得分 ：】" + data.result.RecordInfo.RecordFactor[i].FactorScore + "</br>"; 
					suggestinfo += "【★因子解释 ：】" + data.result.RecordInfo.RecordInfo[i].FactorInfo + "</br>";
					suggestinfo += "【★参考信息 ：】" + data.result.RecordInfo.RecordInfo[i].ReferenceInfo + "</br>";
					suggestinfo += "</br>";
				}
				console.log(suggestinfo);
				$("#suggestinfo").append(suggestinfo);
				
				
			}
			);

	
});

 



