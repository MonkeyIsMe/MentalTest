var cid,cname,cscore,count;
var op = 12;
var row = 1;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"MentalTest/CountTemplate",
			{
				template_name:"",
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.Count;
				count = Math.ceil(sum/5);
				var total = "共" + Math.ceil(sum/5) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);

	
});

$(function () {
    $("input[type='radio']").on("click",function () {
        op = $("input[type='radio']:checked").val();
        var num = parseInt(op,10);
        for(var i = 2; i <= num; i ++){
        	//console.log("1. = " + "#ch_" + i);
			$("#ch_" + i).css("display","block");
        }
        for(var i = num + 1; i <= 12; i ++){
        	//console.log("2. = " + "#ch_" + i);
        	$("#ch_" + i).css("display","none");
        }
    })
})

//初始化选项列表
function InitChoiceTable(){
    $.post(
            "MentalTest/QueryChoiceByProblem",
            {
            	problem_id:pid
            },
            function(data) {
                var data = JSON.parse(data);
                //console.log(data);
                if(data.Count != 0){
                    for( var i = 0; i < data.Array.length; i++ ) {
                        //动态创建一个tr行标签,并且转换成jQuery对象
                        var $trTemp = $("<tr ></tr>");
                        //往行里面追加 td单元格
    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].choiceId +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].choiceInfo +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].choiceScore +"</td>");
    			        $trTemp.append("<td>" + 
    			        		'<a><span class="glyphicon glyphicon-eye-open" style="cursor:pointer;margin-left:18px" ></span></a>'
    			        		+"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#ChoiceList");
                    }
                }

            }
        );
}

function NextPage(){
	//alert(count);
	if(row == count){
		alert("没有后一页了");
	}
	else{
		$("#temp_info").html("");
		row ++;
		   $.post(
			        "MentalTest/QueryTemplatePageSize",
			        {
			            page:row,
			            limit:5,
			            template_name:"",
			        },
			        function(data) {
			            var data = JSON.parse(data);
			            //console.log(data.Array);
			            for(var i = 0; i < data.Array.length; i ++){
			                var html = "<div class='temp'>"
						    html += "<div class='float'>模板名称：</div>";
					        html += "<div class='float' style='color:red'>"+data.Array[i].TemplateName+"</div><br>";
			                for(var j = 0; j < data.Array[i].ChoiceList.length; j ++){
			                    html += "<div class='float'>选项内容：</div>";
			                    html += "<div class='float'>" + data.Array[i].ChoiceList[j].ChoiceInfo + "</div>";
			                    html += "<div class='float'>，</div>";
			                    html += "<div class='float'>选项分值：</div>";
			                    html += "<div>" + data.Array[i].ChoiceList[j].ChoiceScore + "</div>";
			                }
			                html += "<button class='btn btn-default btn-sm' style='margin-top: -32px;margin-right:10px;float: right' onclick='ChooseTemplate("+ data.Array[i].TemplateId +")'>选择</button>"
			                html += "</div>";
			                $("#temp_info").append(html);
			            }
			        }
			    );
	}
}

function PrevPage(){
	if(row == 1){
		alert("没有前一页了");
	}
	else{
		$("#temp_info").html("");
		row --;
		   $.post(
			        "MentalTest/QueryTemplatePageSize",
			        {
			            page:row,
			            limit:5,
			            template_name:"",
			        },
			        function(data) {
			            var data = JSON.parse(data);
			            console.log(data.Array);
			            for(var i = 0; i < data.Array.length; i ++){
			                var html = "<div class='temp'>"
						    html += "<div class='float'>模板名称：</div>";
					        html += "<div class='float' style='color:red'>"+data.Array[i].TemplateName+"</div><br>";
			                for(var j = 0; j < data.Array[i].ChoiceList.length; j ++){
			                    html += "<div class='float'>选项内容：</div>";
			                    html += "<div class='float'>" + data.Array[i].ChoiceList[j].ChoiceInfo + "</div>";
			                    html += "<div class='float'>，</div>";
			                    html += "<div class='float'>选项分值：</div>";
			                    html += "<div>" + data.Array[i].ChoiceList[j].ChoiceScore + "</div>";
			                }
			                html += "<button class='btn btn-default btn-sm' style='margin-top: -32px;margin-right:10px;float: right' onclick='ChooseTemplate("+ data.Array[i].TemplateId +")'>选择</button>"
			                html += "</div>";
			                $("#temp_info").append(html);
			            }
			        }
			    );
	}
}

function ReloadTemplateList(){
	
	$.ajaxSettings.async = false;
	$.post(
			"MentalTest/CountTemplate",
			{
				template_name:"",
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.Count;
				count = Math.ceil(sum/5);
				var total = "共" + Math.ceil(sum/5) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
	$("#temp_info").html("");
	   $.post(
		        "MentalTest/QueryTemplatePageSize",
		        {
		            page:row,
		            limit:5,
		            template_name:"",
		        },
		        function(data) {
		            var data = JSON.parse(data);
		            //console.log(data.Array);
		            for(var i = 0; i < data.Array.length; i ++){
		                var html = "<div class='temp'>"
					    html += "<div class='float'>模板名称：</div>";
				        html += "<div class='float' style='color:red' >"+data.Array[i].TemplateName+"</div><br>";
		                for(var j = 0; j < data.Array[i].ChoiceList.length; j ++){
		                    html += "<div class='float'>选项内容：</div>";
		                    html += "<div class='float'>" + data.Array[i].ChoiceList[j].ChoiceInfo + "</div>";
		                    html += "<div class='float'>，</div>";
		                    html += "<div class='float'>选项分值：</div>";
		                    html += "<div>" + data.Array[i].ChoiceList[j].ChoiceScore + "</div>";
		                }
		                html += "<button class='btn btn-default btn-sm' style='margin-top: -32px;margin-right:10px;float: right' onclick='ChooseTemplate("+ data.Array[i].TemplateId +")'>选择</button>"
		                html += "</div>";
		                $("#temp_info").append(html);
		            }
		        }
		    );
}


$(document).ready(function(){
	
		//加载模板
	   $.post(
		        "MentalTest/QueryTemplatePageSize",
		        {
		            page:row,
		            limit:5,
		            template_name:"",
		        },
		        function(data) {
		            var data = JSON.parse(data);
		            //console.log(data.Array);
		            for(var i = 0; i < data.Array.length; i ++){
		                var html = "<div class='temp'>";
				        html += "<div class='float'>模板名称：</div>";
				        html += "<div class='float' style='color:red'>"+data.Array[i].TemplateName+"</div><br>";
		                for(var j = 0; j < data.Array[i].ChoiceList.length; j ++){
		                    html += "<div class='float'>选项内容：</div>";
		                    html += "<div class='float'>" + data.Array[i].ChoiceList[j].ChoiceInfo + "</div>";
		                    html += "<div class='float'>，</div>";
		                    html += "<div class='float'>选项分值：</div>";
		                    html += "<div>" + data.Array[i].ChoiceList[j].ChoiceScore + "</div>";
		                }
		                html += "<button class='btn btn-default btn-sm' style='margin-top: -32px;margin-right:10px;float: right' onclick='ChooseTemplate("+ data.Array[i].TemplateId +")'>选择</button>"
		                html += "</div>";
		                $("#temp_info").append(html);
		            }
		        }
		    );
	   

	   
	//增加模板
	$("#add_temp").click(function(){
		var tempinfo = [];
		var flag = 1;
		//alert(op);
		
		for(var i = 1; i <= op; i ++){
			var name = $("#name" + i).val();
			var score = $("#score" + i).val();
			if(name == "" || name == null || score == null || score == ""){
				alert("所填项均为非空");
				for(var i in tempinfo){
					delete tempinfo[i];
					flag = 0;
					return ;
				}
			}
			else tempinfo.push({"choice_info": name, "choice_score": score,"choice_sub":"0","choice_type":"0"})
		}
		
		
		var temp_name = $("#temp_name").val();
		
		if(temp_name == null || temp_name == ""){
			alert("模板名字非空！");
			flag = 0;
		}
		
		if(flag == 1){
			//alert(123);
			var temp = JSON.stringify(tempinfo);
			//console.log(temp);
		    $.post(
		            "MentalTest/AddTemplate",
		            {
		            	template_name:temp_name,
		            	choice_info:temp,
		            },
					function(data){
						var data = JSON.parse(data);
						var result = data.result;
						if(result == "Success"){
							alert("添加模板成功");
							ReloadTemplateList();
						}
						else{
							alert("添加模板失败！");
						}
					}
		        );
		}

	})
	
	
	//添加选项
	$("#add_choice").click(function(){
		$("#pro_info").val("");
		$("#pro_score").val("");
		  if(pid == 0){
			  alert("请先选择题目");
		  }
		  else{
			  //alert(pid);
			  var choice_info = $("#pro_info").val();
			  var choice_score = $("#pro_score").val();
			  //$('#add_Modal').modal('toggle');
			    $.post(
			            "MentalTest/AddChoice",
			            {
			            	choice_flag:"problem",
			            	choice_score:choice_score,
			            	choice_sub:0,
			            	choice_info:choice_info,
			            	choice_type:1,
			            	flag_id:pid,
			            },
						function(data){
							var data = JSON.parse(data);
							var result = data.result;
							if(result == "Success"){
								alert("添加成功");
								$("#ChoiceList").html("");
								InitChoiceTable();

								//data-dismiss="modal"
							}
							else{
								alert("添加失败！");
							}
						}
			        );
		  }
	})
	
	  $("#choiceTable").on('click','.delete_choice',function(){
		  //alert(111)
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1 = currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var col2 = currentRow.find("td:eq(1)").text(); //获得当前行第一个TD值
		    //alert(col1);
		    
		    cid = col1;
		    cname = col2;
		    $("#del_choice_info").empty();
		    $("#del_choice_info").append("是否删除编号为" + cid + "信息为：" + cname + "的选项");
	  });
	
	  $("#choiceTable").on('click','.update_choice',function(){
		  //alert(111)
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1 = currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var col2 = currentRow.find("td:eq(1)").text(); //获得当前行第一个TD值
		    var col3 = currentRow.find("td:eq(2)").text(); //获得当前行第一个TD值
		    //alert(col1);
		    
		    cid = col1;
		    cname = col2;
		    cscore = col3;
			$("#up_ch_info").val("");
			$("#up_ch_score").val("");
			$("#up_ch_info").val(cname);
			$("#up_ch_score").val(cscore);
	  });
	
	  
	  
		$("#up_choice").click(function(){
			
			var choice_name = $("#up_ch_info").val();
			var choice_score = $("#up_ch_score").val();
			alert(choice_score);
		    $.post(
		            "MentalTest/UpdateChoice",
		            {
		            	choice_id:cid,
		            	choice_name:choice_name,
		            	choice_score:choice_score,
		            },
					function(data){
						var data = JSON.parse(data);
						var result = data.result;
						if(result == "Success"){
							alert("更新成功");
							$("#ChoiceList").html("");
							InitChoiceTable();

							//data-dismiss="modal"
						}
						else{
							alert("更新失败！");
						}
					}
		        );
		})
	  

		
	$("#del_choice").click(function(){
	    $.post(
	            "MentalTest/DeleteChoice",
	            {
	            	choice_id:cid
	            },
				function(data){
					var data = JSON.parse(data);
					var result = data.result;
					if(result == "Success"){
						alert("删除成功");
						$("#ChoiceList").html("");
						InitChoiceTable();

						//data-dismiss="modal"
					}
					else{
						alert("删除失败！");
					}
				}
	        );
	})
})
