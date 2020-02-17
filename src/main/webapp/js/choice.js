var cid,cname,cscore;

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
    			        		'<a><span class="delete_choice glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#delchoice_Modal"></span></a>'
    			        		 + '<a><span class="update_choice glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#upchoice_Modal"></span></a>'
    			        		 + '<a><span class="glyphicon glyphicon-eye-open" style="cursor:pointer;margin-left:18px" ></span></a>'
    			        		+"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#ChoiceList");
                    }
                }

            }
        );
}

$(document).ready(function(){
	
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
