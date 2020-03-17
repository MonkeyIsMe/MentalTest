var url = decodeURI(window.location.href);
 
URL = url.split("?")[1]; 
var a = URL.split("&");       
var PatientId = a[0].split("=")[1]; 
var ScaleId = a[1].split("=")[1];

var testinfo = []; //用于存放题目选项信息
var testlength = 0; //用于存放量表题目选项信息的最大长度
var countlength = 0; //用户记录当前的长度
var record = [];

//console.log(ScaleId + " " + PatientId);

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"MentalTest/QueryScaleInfo",
			{
				scale_id:ScaleId,
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				testinfo = data.result;
				testlength = data.result.length;
			}
			);
	
	//console.log(testinfo);
	//console.log(testlength);
	//初始化第一个选项
	$("#problem_name").append("题目描述：" + testinfo[0].ProblemName);
	for(var i = 0;i < testinfo[0].ChoiceInfo.length; i ++){
		var num = i + 1;
		var html = "<li class=radio_style onclick=load("+ countlength +","+testinfo[0].ChoiceInfo[i].choiceScore +"," + testinfo[0].ProblemId +")>" ;
		html += "第"+ num +"个选项：<input type=radio name=score value="+ testinfo[0].ChoiceInfo[i].choiceScore +"," + testinfo[0].ProblemId  + ">";
		html +=	"<label >"+ testinfo[0].ChoiceInfo[i].choiceInfo +"</label></li>"
		$("#choice_info").append(html);
		
	}
	$("#process").html("");
	var process = GetPercent(countlength,testlength);
	var process_info = "当前第" + (countlength + 1) + "题，已完成:"+ countlength +"题，完成率为:" + process + "，总共" +testlength + "题。";
	$("#process").append(process_info);
});





function load(countlength,socre,id){
	//alert(a + " " + b + " " + c);
	countlength += 1;
	$("#problem_name").empty();
	$("#choice_info").empty();
	record.push({Score: socre, ProblemId: id});
	if(countlength == testlength){
		//console.log(record);
		$.post(
				"MentalTest/DoScaleTest",
				{
					scale_id:ScaleId,
					patient_id:PatientId,
					choice_info:JSON.stringify(record)
				},
				function(data){
					var data = JSON.parse(data);
					console.log(data.RecordId);
					var edit_url = "DisplayRecord.html?rid=" + data.RecordId;
					window.location.href = edit_url;
				}
				);
	}
	else{
		
		$("#problem_name").append("题目描述：" + testinfo[countlength].ProblemName);
		for(var i = 0;i < testinfo[countlength].ChoiceInfo.length; i ++){
			var num = i + 1;
			var html = "<li class=radio_style onclick=load("+ countlength +","+testinfo[countlength].ChoiceInfo[i].choiceScore +"," + testinfo[countlength].ProblemId +")>" ;
			html += "第"+ num +"个选项：<input type=radio name=score value="+ testinfo[countlength].ChoiceInfo[i].choiceScore +"," + testinfo[countlength].ProblemId  + ">";
			html +=	"<label >"+ testinfo[0].ChoiceInfo[i].choiceInfo +"</label></li>"
			$("#choice_info").append(html);
		}
		$("#process").html("");
		var process = GetPercent(countlength,testlength);
		var process_info = "当前第" + (countlength + 1) + "题，已完成:"+ countlength +"题，完成率为:" + process + "，总共" +testlength + "题。";
		$("#process").append(process_info);
	}
}

function GetPercent(num, total) {
    num = parseFloat(num);
    total = parseFloat(total);
    if (isNaN(num) || isNaN(total)) {
        return "-";
    }
    return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00)+"%";
}
