/**
 * Created by CallMeDad on 2020/02/17.
 */

var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?pid=");
var pid = argsIndex[1];

var row = 1;  //页数
var count; //总记录数
var sid,sname;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"MentalTest/CountScale",
			{
	            scale_flag:"null",
	            flag_id:0,
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
        "MentalTest/QueryScalePageSize",
        {
            page:row,
            limit:25,
            scale_flag:"null",
            flag_id:0,
            
        },
        function(data) {
            var data = JSON.parse(data);
            //console.log(data);
                for( var i = 0; i < data.Array.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">"+ data.Array[i].ScaleId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">"  + data.Array[i].ScaleName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">"  + data.Array[i].ScaleNumber +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">" +data.Array[i].ScaleUpdateTime  +"</td>");
			        $trTemp.append("<td>" + 
			        		'<a><span class="dotest" style="cursor:pointer;" data-toggle="modal" data-target="#del_Modal">开始测试</span></a>'
			        		+"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#ScaleList");
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
		$("#NowPage").html("");
		$("#NowPage").append("，当前第" + row + "页");
		$("#ScaleList").html("");
	    $.post(
	            "/MentalTest/QueryScalePageSize",
	            {
	                page:row,
	                limit:25,
	                scale_flag:"null",
	                flag_id:0,
	                
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                console.log(data);
	                    for( var i = 0; i < data.Array.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">"+ data.Array[i].ScaleId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">"  + data.Array[i].ScaleName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">"  + data.Array[i].ScaleNumber +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">" +data.Array[i].ScaleUpdateTime  +"</td>");
	    			        $trTemp.append("<td>" + 
	    			        		'<a><span class="dotest" style="cursor:pointer;" data-toggle="modal" data-target="#del_Modal">开始测试</span></a>'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#ScaleList");
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
		$("#NowPage").html("");
		$("#NowPage").append("，当前第" + row + "页");
		$("#ScaleList").html("");
	    $.post(
	            "/MentalTest/QueryScalePageSize",
	            {
	                page:row,
	                limit:25,
	                scale_flag:"null",
	                flag_id:0,
	                
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                console.log(data);
	                    for( var i = 0; i < data.Array.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">"+ data.Array[i].ScaleId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">"  + data.Array[i].ScaleName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">"  + data.Array[i].ScaleNumber +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;font-size:14px"  + ">" +data.Array[i].ScaleUpdateTime  +"</td>");
	    			        $trTemp.append("<td>" + 
	    			        		'<a><span class="dotest" style="cursor:pointer;" data-toggle="modal" data-target="#del_Modal">开始测试</span></a>'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#ScaleList");
	                    }
	            }
	        );
	}

}

$(document).ready(function(){
	
	  
	  $("#myTable").on('click','.dotest',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1 = currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    
		    sid = col1;
		    
			$.post(
					"MentalTest/QuerySingleScale",
					{
						scale_id:sid,
					},
					function(data){
						var data = JSON.parse(data);
						//console.log(data);
						$("#scale_info").html("");
						$("#scale_guide").html("");
						$("#scale_info").append(data.ScaleInfo);
						$("#scale_guide").append(data.ScaleGuide);
					}
					);
		    
		  });
	  
	  $("#todotest").click(function(){
			var edit_url = "ScaleTest.html?pid=" + pid + "&sid=" + sid;
			window.location.href = edit_url;
	  })
	  
});

