/**
 * Created by CallMeDad on 2020/02/16.
 */

var row = 1;  //页数
var count; //总记录数
var uid,uname,urole,uaccount;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"MentalTest/CountUser",
			{
				
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.Count;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);

	
});


$(function(){
    $.post(
        "MentalTest/QueryUserPageSize",
        {
            page:row,
            limit:25,
            
        },
        function(data) {
            var data = JSON.parse(data);
            //console.log(data);
            for( var i = 0; i < data.Array.length; i++ ) {
                //动态创建一个tr行标签,并且转换成jQuery对象
                var $trTemp = $("<tr ></tr>");
                //往行里面追加 td单元格
		        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].UserId +"</td>");
		        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].UserName +"</td>");
		        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].UserAccount +"</td>");
		        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].UserRole  +"</td>");
		        $trTemp.append("<td>" + 
		        		'<a><span class="delete glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#del_myModal"></span></a>'
		        		 + '<a><span class="update glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#up_Modal"></span></a>'
		        		 + '<a><span class="pwd glyphicon glyphicon-edit" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#myModal"></span></a>'
		        		+"</td>");
                // $("#J_TbData").append($trTemp);
                $trTemp.appendTo("#UserList");
            }
        }
    );

});


function PrevPage(){	
	if(row == 1){
		alert("没有前一页了");
	}
	else{
		row--;
		$("#UserList").html("");
	    $.post(
	            "MentalTest/QueryUserPageSize",
	            {
	                page:row,
	                limit:25,
	                
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
                    for( var i = 0; i < data.Array.length; i++ ) {
                        //动态创建一个tr行标签,并且转换成jQuery对象
                        var $trTemp = $("<tr ></tr>");
                        //往行里面追加 td单元格
    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].UserId +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].UserName +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].UserAccount +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].UserRole  +"</td>");
    			        $trTemp.append("<td>" + 
    			        		'<a><span class="delete glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#del_myModal"></span></a>'
    			        		 + '<a><span class="update glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#up_Modal"></span></a>'
    			        		 + '<a><span class="pwd glyphicon glyphicon-edit" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#myModal"></span></a>'
    			        		+"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#UserList");
                    }
	            }
	        );
	}
}

function NextPage(){
	if(row == count){
		alert("没有后一页了");
	}
	else{
		row ++;
		$("#UserList").html("");
	    $.post(
	            "MentalTest/QueryUserPageSize",
	            {
	                page:row,
	                limit:25,
	                
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.Array.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].UserId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].UserName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].UserAccount +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].UserRole  +"</td>");
	    			        $trTemp.append("<td>" + 
	    			        		'<a><span class="delete glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#del_myModal"></span></a>'
	    			        		 + '<a><span class="update glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#up_Modal"></span></a>'
	    			        		 + '<a><span class="pwd glyphicon glyphicon-edit" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#myModal"></span></a>'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#UserList");
	                    }
	            }
	        );
	}

}

$(document).ready(function(){
	
	  
	  $("#myTable").on('click','.delete',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1 = currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var col2 = currentRow.find("td:eq(1)").text(); //获得当前行第一个TD值
		    
		    uid = col1;
		    uname = col2;
		    $("#del_user_info").empty();
		    $("#del_user_info").append("是否删除名字为: "+ uname + "的用户");
		  });
	  
	  $("#myTable").on('click','.pwd',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1 = currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    
		    uid = col1;
		  });
	  
	  $("#myTable").on('click','.update',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr");
		    
		    var col1 = currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var col2 = currentRow.find("td:eq(1)").text(); //获得当前行第一个TD值
		    var col3 = currentRow.find("td:eq(2)").text(); //获得当前行第一个TD值
		    var col4 = currentRow.find("td:eq(3)").text(); //获得当前行第一个TD值
		    
		    uid = col1;
		    uname = col2;
		    urole = col4;
		    
		    $("#up_name").val(uname);
		    $("#up_role").val(urole);
		    
		  });
	  
	 
	  $("#del_user").click(function(){
		  	$.post(
					"MentalTest/DeleteUser",
					{
						user_id:uid,
					},
					function(data){
						var data = JSON.parse(data);
						var result = data.result;
						if(result == "Success"){
							alert("删除成功！");
						    var url = "User.html";
						    window.location.replace(url);
						}
						else{
							alert("删除失败！");
						    var url = "User.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	  
	  $("#add_user").click(function(){
			
			var user_name = $("#add_name").val();
			var user_account = $("#add_account").val();
			var user_password = $("#add_password").val();
			var user_role = $("#add_role").val();
			
		  	$.post(
					"MentalTest/AddUser",
					{
						user_name:user_name,
						user_account:user_account,
						user_password:user_password,
						user_role:user_role,
					},
					function(data){
						var data = JSON.parse(data);
						var result = data.result;
						if(result == "Success"){
							alert("添加成功！");
						    var url = "User.html";
						    window.location.replace(url);
						}
						else if(result ="Already"){
							alert("账号已存在！");
						    var url = "User.html";
						    window.location.replace(url);
						}
						else{
							alert("添加失败！");
						    var url = "User.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	  
		  $("#up_user").click(function(){
			  //alert(uid);
			var user_name = $("#up_name").val();
			var user_role = $("#up_role").val();
			
		  	$.post(
					"MentalTest/UpdateUserInfo",
					{
						user_id:uid,
						user_name:user_name,
						user_role:user_role,
					},
					function(data){
						var data = JSON.parse(data);
						var result = data.result;
						if(result == "Success"){
							alert("更新成功！");
						    var url = "User.html";
						    window.location.replace(url);
						}
						else{
							alert("更新失败！");
						    var url = "User.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	  
	  $("#up_pwd").click(function(){
			  //alert(uid);
			var user_password = $("#up_password").val();
			
		  	$.post(
					"MentalTest/UpdateUserPassword",
					{
						user_id:uid,
						user_password:user_password,
					},
					function(data){
						var data = JSON.parse(data);
						var result = data.result;
						if(result == "Success"){
							alert("更新密码成功！");
						    var url = "User.html";
						    window.location.replace(url);
						}
						else{
							alert("更新密码失败！");
						    var url = "User.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	  
});

function refresh(){
	window.location.replace("User.html");
}