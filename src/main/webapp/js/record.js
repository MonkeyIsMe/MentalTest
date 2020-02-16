/**
 * Created by CallMeDad on 2020/02/15.
 */

var row = 1;  //页数
var count; //总记录数
var rid,sname,pname;




$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"MentalTest/CountRecord",
			{
	            flag_id:0,
	            record_flag:"null",
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
        "MentalTest/QueryRecordPageSize",
        {
            page:row,
            limit:25,
            flag_id:0,
            record_flag:"null",
        },
        function(data) {
            var data = JSON.parse(data);
            //console.log(data);
                for( var i = 0; i < data.Array.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
                    
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].RecordId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].ScaleName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].RecordTime  +"</td>");
			        $trTemp.append("<td>" + 
			        		'<a><span class="delete glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#del_myModal"></span></a>'
			        		 + '<a><span class="update glyphicon glyphicon-list-alt" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#up_Modal"></span></a>'
			        		+"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#RecordList");
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
		$("#RecordList").html("");
	    $.post(
	            "MentalTest/QueryRecordPageSize",
	            {
	                page:row,
	                limit:25,
	                flag_id:0,
	                record_flag:"null",
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.Array.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	                        
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].RecordId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].ScaleName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].RecordTime  +"</td>");
	    			        $trTemp.append("<td>" + 
	    			        		'<a><span class="delete glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#del_myModal"></span></a>'
	    			        		 + '<a><span class="update glyphicon glyphicon-list-alt" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#up_Modal"></span></a>'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#RecordList");
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
		$("#RecordList").html("");
	    $.post(
	            "MentalTest/QueryRecordPageSize",
	            {
	                page:row,
	                limit:25,
	                flag_id:0,
	                record_flag:"null",
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.Array.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	                        
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].RecordId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].ScaleName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].RecordTime  +"</td>");
	    			        $trTemp.append("<td>" + 
	    			        		'<a><span class="delete glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#del_myModal"></span></a>'
	    			        		 + '<a><span class="update glyphicon glyphicon-list-alt" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#up_Modal"></span></a>'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#RecordList");
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
		    var col3 = currentRow.find("td:eq(2)").text(); //获得当前行第一个TD值
		    
		    rid = col1;
		    pname = col2;
		    sname = col3;
		    
		    $("#del_record_info").empty();
		    $("#del_record_info").append("是否删除编号为: "+ rid + "的,病人" + pname +"所做的量表" + sname + "的记录");
		  });
	  
	  
	 
	  $("#del_Record").click(function(){
		  	$.post(
					"MentalTest/DeleteRecord",
					{
						record_id:rid,
					},
					function(data){
						var data = JSON.parse(data);
						var result = data.result;
						if(result == "Success"){
							alert("删除成功！");
						    var url = "Record.html";
						    window.location.replace(url);
						}
						else{
							alert("删除失败！");
						    var url = "Record.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	  

	  
});

function refresh(){
	window.location.replace("Record.html");
}