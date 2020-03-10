$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"MentalTest/QuerySingleRecord",
			{
				record_id:1
			},
			function(data){
				var data = JSON.parse(data);
				console.log(data.result);
				console.log(data.result.RecordInfo.RecordInfo);
				
				//处理病人信息
				var patient = data.result.PatientInfo;
				var patientinfo1 = "姓名 ：" + patient.PatientName +"　　　　身份证号码：" + patient.PatientIndentity + "　　　　住院号/门诊号：" + patient.PatientNumber
				var patientinfo2 = "性别 ：" + patient.PatientGender + "　　　　民族 ：" + patient.PatientNation+"　　　　出生年月：" + patient.PatientBirthday + "　　　　住址：" + patient.PatientPlace;;
				$("#patientinfo1").append(patientinfo1);
				$("#patientinfo2").append(patientinfo2);
				
				//处理因子信息
				var factorinfo = "";
				for(var i = 0; i < data.result.RecordInfo.RecordFactor.length; i ++){
					factorinfo += "因子名称 ：" + data.result.RecordInfo.RecordFactor[i].FactorName; 
					factorinfo += "　　　　因子得分 ：" + data.result.RecordInfo.RecordFactor[i].FactorScore + "　　　　"; 
					if(i % 2 != 0) factorinfo += "</br>"; 
				}
				$("#factorinfo").append(factorinfo);
				
				//处理参考信息
				var suggestinfo = "";
				for(var i = 0; i < data.result.RecordInfo.RecordInfo.length; i ++){
					suggestinfo += "因子名称 ：</br>" + data.result.RecordInfo.RecordFactor[i].FactorName + "</br>"; 
					suggestinfo += "因子得分 ：</br>" + data.result.RecordInfo.RecordFactor[i].FactorScore + "</br>"; 
					suggestinfo += "因子解释 ：</br>" + data.result.RecordInfo.RecordInfo[i].FactorInfo + "</br>";
					suggestinfo += "参考信息 ：</br>" + data.result.RecordInfo.RecordInfo[i].ReferenceInfo + "</br>";
					suggestinfo += "</br>";
				}
				console.log(suggestinfo);
				$("#suggestinfo").append(suggestinfo);
				
				
			}
			);

	
});

 



