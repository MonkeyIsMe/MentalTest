var problem = [];
var choice = [];
var pid = 0,page = 0;

$(function(){
	$("#clist").load("clist.html");
    $.post(
        "MentalTest/QueryProblemByScale",
        {
        	scale_id:1
        },
        function(data) {
            var data = JSON.parse(data);
            //console.log(data);
                for( var i = 0; i < data.Array.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    problem.push({ProblemName: data.Array[i].problemName, ProblemId: data.Array[i].problemId});
			        $trTemp.append("<td class=pro style=" + "text-align:center"  + ">"+  data.Array[i].problemName +"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#ProbelmList");
                }
        }
    );

});

function InitProblemTable(){
    $.post(
            "MentalTest/QueryProblemByScale",
            {
            	scale_id:1
            },
            function(data) {
                var data = JSON.parse(data);
                //console.log(data);
                    for( var i = 0; i < data.Array.length; i++ ) {
                        //动态创建一个tr行标签,并且转换成jQuery对象
                        var $trTemp = $("<tr ></tr>");
                        problem.push({ProblemName: data.Array[i].problemName, ProblemId: data.Array[i].problemId});
    			        $trTemp.append("<td class=pro style=" + "text-align:center"  + ">"+  data.Array[i].problemName +"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#ProbelmList");
                    }
            }
        );
}



function  ChooseTemplate(id) {
    //alert(id);
	if(pid == 0){
		alert("请先选择题目"); 
	}
	else{
		//alert(pid + " " + id);
	    $.post(
	            "MentalTest/ChooseTemplate",
	            {
	            	problem_id:pid,
	            	template_id:id,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                var result = data.result;
					if(result == "Success"){
						alert("选择成功");
					}
					else{
						alert("选择失败！");
					}
	            }
	        );
	}
}

$(document).ready(function(){
	  $("#myTable").on('click','.pro',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1 = currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    for (var i in problem) {
		    	//console.log(problem[i]);
		    	if(problem[i].ProblemName == col1){
		    		pid = problem[i].ProblemId;
		    	}
		    	$("#ChoiceList").html("");
		    }
		    
		    $.post(
		            "MentalTest/QuerySingleProblem",
		            {
		            	problem_id:pid
		            },
		            function(data) {
		                var data = JSON.parse(data);
		                //console.log(data);
		                console.log(data.ProblemInfo.ProblemFlag);
		                $("#ProblemInfo").val("");
		                $("#ProblemNumber").val("");
		                if(data.ProblemInfo.ProblemFlag == 1){
		                	$("#ProblemFlag").prop('checked', true)
		                	//alert(1);
		                }
		                else{
		                	//alert(0);
		                	$("#ProblemFlag").prop('checked', false);
		                }
		               
		                $("#ProblemType").find("option[value='"+ data.ProblemInfo.ProblemType +"']").attr("selected",true);
		                $("#ProblemInfo").val(data.ProblemInfo.ProblemName);
		                $("#ProblemNumber").val(data.ProblemInfo.ProblemNumber);
		            }
		        );
		    
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
	        			        		/*'<a><span class="delete_choice glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#delchoice_Modal"></span></a>'
	        			        		 + '<a><span class="update_choice glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#upchoice_Modal"></span></a>'*/
	        			        		  '<a><span class="glyphicon glyphicon-eye-open" style="cursor:pointer;margin-left:18px" ></span></a>'
	        			        		 +"</td>");
	                            // $("#J_TbData").append($trTemp);
	                            $trTemp.appendTo("#ChoiceList");
	                        }
		                }

		            }
		        );
		    
		    
	  });
	  
	  $("#add_problem").click(function(){
		  pid = 0;
          $("#ProblemInfo").val("");
          $("#ProblemNumber").val("");
	  })
	  
	  $("#del_problem").click(function(){
		  if(pid == 0){
			  alert("请先选择题目");
		  }
		  else{
			    $.post(
			            "MentalTest/DeleteProblem",
			            {
			            	problem_id:pid,
			            },
						function(data){
							var data = JSON.parse(data);
							var result = data.result;
							if(result == "Success"){
								alert("删除成功");
								$("#ProbelmList").html("");
								InitProblemTable();
							}
							else{
								alert("删除失败！");
							}
						}
			        );
		  }
	  })
	  

	  
	  $("#save_problem").click(function(){
		 if(pid == 0){
			 var problem_flag = 0;
			 var problem_name = $("#ProblemInfo").val();
			 var problem_number = $("#ProblemNumber").val();
			 var problem_type = $('#PrblemType option:selected') .val();
			 var flag = $("input[type='checkbox']").is(':checked');
			 if(flag == true) prblem_flag = 1;
			    $.post(
			            "MentalTest/AddProblem",
			            {
			            	scale_id:1,
			            	problem_name:problem_name,
			            	problem_type:problem_type,
			            	problem_flag:problem_flag,
			            	problem_number:problem_number,
			            	template_id:0
			            },
						function(data){
							var data = JSON.parse(data);
							var result = data.result;
							if(result == "Success"){
								alert("添加题目成功");
								$("#ProbelmList").html("");
								InitProblemTable();
							}
							else{
								alert("添加题目失败！");
							}
						}
			        );
		 }
		 else{
			 var problem_flag = 0;
			 var problem_name = $("#ProblemInfo").val();
			 var problem_number = $("#ProblemNumber").val();
			 var problem_type = $('#PrblemType option:selected') .val();
			 var flag = $("input[type='checkbox']").is(':checked');
			 if(flag == true) prblem_flag = 1;
			    $.post(
			            "MentalTest/UpdateProblemInfo",
			            {
			            	problem_id:pid,
			            	problem_name:problem_name,
			            	problem_type:problem_type,
			            	problem_flag:problem_flag,
			            	problem_number:problem_number,
			            	template_id:0
			            },
						function(data){
							var data = JSON.parse(data);
							var result = data.result;
							if(result == "Success"){
								alert("更新题目成功");
								$("#ProbelmList").html("");
								InitProblemTable();
							}
							else{
								alert("更新题目失败！");
							}
						}
			        );
		 }
	  })
});