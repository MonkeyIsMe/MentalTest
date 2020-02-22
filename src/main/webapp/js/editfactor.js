var advice,add,delBtn,addfac,lis,saveQuesBtn;
var facArray,nowIndex=0,parLen=0,tabIndex;
var check1,check2,check3,check4;
var next;
var facArr=new Array();//因子信息
var paraArr=new Array();//参考表信息
var queArr=new Array();//总题目信息
var facQueArr=new Array();//因子关联的题号
var problem_info=new Array();

window.onload=function(){
	next=document.getElementById("olClass");
	advice=document.getElementById("adv_1");
    add=document.getElementById("addBtn");
    addfac=document.getElementById("addfacBtn");
    saveQuesBtn=document.getElementById("saveQueBtn");
    
    getAllQuestion();//查询所有题目
    
    queryFacTableInfo();
    
    saveQuesBtn.onclick=function(){
    	saveQuestionsBtn();
    }
    
    addfac.onclick=function(){//添加因子
    	addFac();
    }
    
    saveFacInfo();//保存因子信息
    
	add.onclick=function(){//添加参考意见表
        addTable();
        delBtn=document.getElementsByName("delBtn");
	};	
}

/**
 * 获取当前因子已关联题目
 * */
function getNowquestions(){
	if(nowIndex<facArr.length){
		$.ajaxSettings.async=false;
	$.post(
		"http://47.105.136.92/MentalTest/QueryProblemByFactor",
		{
			factor_id:facArr[nowIndex].factorId,
		},
		function(data){
			 var data=JSON.parse(data);
			 facQueArr=data.Array;
           	    console.log("关联题号：")
           	    console.log(facQueArr);
           	    if(facQueArr!=null){
           	    for(let i=0;i<facQueArr.length;i++){
           	    	for(let j=0;j<queArr.length;j++){
           	    		if(facQueArr[i].problemNumber==queArr[j].ProblemNumber){
           	    			var Que="q_"+j;
           	    			$("#"+Que).css("background-color",'rgb(65,199,219)');
           	    		}
           	    	}
           	      }
           	   }
		}	
	 )
	}
}

/**
 * 响应保存题目按钮
 * */
function saveQuestionsBtn(){
	   if(nowIndex<facArr.length){
	   	  var question=new Array();
		 for(let i=0;i<queArr.length;i++){
           	var Que="q_"+i;
           	var bgcValue=$('#'+Que).css('background-color');
           	   if(bgcValue=='rgb(65, 199, 219)'){
           	   var box={"problem_id":queArr[i].ProblemId,"problem_number":queArr[i].ProblemNumber};
         	    question.push(box);
           	    }
           	}
		    problem_info=JSON.stringify(question);
           	$.ajax({
            type : "POST",
            url : "http://47.105.136.92/MentalTest/AddOrUpdateFactorProblem",
            dataType : "json",
            data : {
               factor_id:facArr[nowIndex].factorId,
               problem_info:problem_info
             },
             success : function(data) {
             alert("保存题目成功")
           	  console.log(data);
             }, 
             fail:function(){
             	console.log("获取总题数失败！")
             }
          });
	   }else{
	   	alert("请先保存因子信息");
	   }
}

/**
 * 获取总题数的接口
 * */
function getAllQuestion(){
	$.ajax({
            type : "POST",
            url : "http://47.105.136.92/MentalTest/QueryRelationProblem",
            dataType : "json",
            data : {
               scale_id:39,
             },
           success : function(data) {
           	   if(data.Array!=null){
           	   	 queArr=data.Array;
    	       console.log(queArr);
    	       var quesNum=document.getElementById("quesNum");
    	       for(let i=0;i<queArr.length;i++){
    	       	  var span=document.createElement("span");
    	       	  span.innerHTML=queArr[i].ProblemNumber;
    	       	  span.style.marginLeft="20px";
    	       	  var Que="q_"+i;
    	       	  span.setAttribute("id",Que);
    	       	  span.setAttribute("class","allQue");
    	       	  quesNum.appendChild(span);
    	       }
    	       selQuestions();
           	   }
             }, 
             fail:function(){
             	console.log("获取总题数失败！")
             }
          });
  }


/**
 * 题目选中响应事件
 * */
function selQuestions(){
	var que=document.getElementsByClassName("allQue");
	for(let i=0;i<que.length;i++){
		que[i].onclick=function(){
			var bgcValue=$(this).css('background-color');
			if(bgcValue=='rgba(0, 0, 0, 0)'){
				this.style.backgroundColor="rgb(65,199,219)";
			}else{
				var confir=confirm("确定取消该选题？");
				if(confir){
					this.style.backgroundColor="rgba(0, 0, 0, 0)";
				}
			}	
		}
	}
}

/**
 * 因子种类选择响应
 * */
function factClass(){
 var ol=document.getElementById("olClass");
 var fourth=document.getElementById("fourthDiv");
 var quesNum=document.getElementById("quesNum");
 lis=document.getElementsByTagName("li");
  for(let i=0;i<lis.length;i++){
  	lis[i].onclick=function(){
  		nowIndex=i;
  		parLen=0;
  		if(i>=facArr.length){
  			$('#facName').val("");
            $('#facIntro').val("");
            $('#levelFac').val("");
            $('#facNum').val("");
            $('#formula').val("");
            fourth.innerHTML="";
  		}else{
  		initialFacInfo(i);
  		fourth.innerHTML="";
  		getParaInfo(i);
  		}
  		quesNum.innerHTML="";
  		getAllQuestion();
  		getNowquestions();
        var now=this;
        next.style.backgroundColor="transparent";
        now.style.backgroundColor="rgb(65,199,219)";
        next=now;

     }
  }
  for(let i=0;i<lis.length;i++){
      lis[i].onmousedown=function(e){
      	if(e.button==2){
      		console.log("aaa");
     	 var confir=confirm("确定删除因子:"+$(this).text()+"?")
     	 if(confir){
     	 	ol.removeChild(this);
     	 	if(i<facArr.length){
     	 		getDeleteFac(i);
     	 	}
     	 }
      	}
       }
     }
}

/*
 * 添加因子
 */
function addFac(){
	var ol=document.getElementById("olClass");
	var li=document.createElement("li");
	var facc=$('#facc').val();
	if(facc!=''){
		li.innerHTML=$('#facc').val();
		li.style.marginTop="10px";
		ol.appendChild(li);
		factClass();
	}
	else alert("因子名称不能为空");
}


/**
 * 保存因子信息
 * */
function saveFacInfo(){
	var save=document.getElementById("saveFacBtn");
	save.onclick=function(){
		if(nowIndex<facArr.length){
			getUpdateFac();
		}
		else{
		    getAddFacIn();
		}				
	}
}

/**
 * 获取添加因子的接口
 * */
function getAddFacIn(){
	var ol=document.getElementById("olClass");
	if($('#facName').val()==''){
		alert("因子名称不能为空");
	}else{
		$.ajax({
            type : "POST",
            url : "http://47.105.136.92/MentalTest/AddFactor",
            dataType : "json",
            data : {
               scale_id:39,
               factor_name:$('#facName').val(),
               factor_info:$('#facIntro').val(),
               factor_balance:$('#levelFac').val(),
               factor_den:$('#facNum').val(),
               factor_formula:$('#formula').val(),
               factor_order:facArr.length,
             },
           success : function(data) {
           	   ol.innerHTML="";
           	   queryFacTableInfo();
               console.log("添加因子成功：");
    	       console.log(data);
    	   
             }, 
             fail:function(){
             	console.log("添加因子失败！")
             }
          });
	}
	
  }

/**
 * 获取更新量表因子的接口
 * */
function getUpdateFac(){
	$.ajax({
    type : "POST",
    url : "http://47.105.136.92/MentalTest/UpdateFactor",
    dataType : "json",
    data : {
       factor_id:facArr[nowIndex].factorId,
       factor_name:$('#facName').val(),
       factor_info:$('#facIntro').val(),
       factor_balance:$('#levelFac').val(),
       factor_den:$('#facNum').val(),
       factor_formula:$('#formula').val(),
       factor_order:nowIndex+1,
    },
    success : function(data) {
    	console.log("更新因子信息成功：");
    	console.log(data);
    }, 
    fail:function(){
    	console.log("更新因子失败");
    }
      });
}


/**
 * 获取获取删除因子接口
 * */
function getDeleteFac(index){
	var factor_id=facArr[index].factorId;
	
	$.ajax({
    type : "POST",
    url : "http://47.105.136.92/MentalTest/DeleteFactor",
    dataType : "json",
    data : {
       factor_id :factor_id,
    },
    success : function(data) {
    	console.log("删除因子成功");
    	console.log(data);
    },  	
      });
}

/**
 * 获取根据量表查询因子的接口
 * */
function queryFacTableInfo(){
	$.ajax({
    type : "POST",
    url : "http://47.105.136.92/MentalTest/QueryFactorByScale",
    dataType : "json",
    data : {
        scale_id :39,
    },
    success : function(data) {
    	if(data.Array!=null){
    	facArr=data.Array;
    	console.log(facArr);
    	var ol=document.getElementById("olClass");
    	for(let i=0;i<facArr.length;i++){
	        var li=document.createElement("li");
	         li.innerHTML=facArr[i].factorName;
	         li.style.marginTop="10px";
	         ol.appendChild(li);
    	}
    	 getParaInfo();//获取因子信息
	     getNowquestions();//获取因子关联的题号
	     initialFacInfo(0);//初始化因子信息
    	}
    	factClass();//因子种类选择
       },	
   });
}

/**
 * 初始化因子信息
 * */
function initialFacInfo(index){
    $('#facName').val(facArr[index].factorName);
    $('#facIntro').val(facArr[index].factorInfo);
    $('#levelFac').val(facArr[index].factorBalance);
    $('#facNum').val(facArr[index].factorDen);
    $('#formula').val(facArr[index].factorFormula);
  
}

/**
 * 初始化参考意见信息
 * */
function initialParaInfo(index){
	var name=['a','b','c','d','e','f','g','h','i','j','k',
	 'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
    var id1,id2,id3,id4,id5,index;
	for(let i=0;i<paraArr.length;i++){
      var scoreHead_1="scoreHead_"+i;
	  var scoreRear_1="scoreRear_"+i;
	  var male_1="male_"+i;
	  var female_1="female_"+i;
	  var refSex_1="refSex_"+i;
	  var refAge_1="refAge_"+i;
	  var ageHead_1="ageHead_"+i;
	  var ageRear_1="ageRear_"+i;
	  var paraInfo_1="paraInfo_"+i;
		  id1=name[i]+1;
		  id2=name[i]+2;
		  id3=name[i]+3;
		  id4=name[i]+4;
		  id5=name[parLen]+5;
		  index="adv"+i;
		   var subInfo=[index,id1,id2,id3,id4,id5,scoreHead_1,scoreRear_1,refSex_1,male_1,
	 female_1,refAge_1,ageHead_1,ageRear_1,paraInfo_1];
		  var par=paraArr[i]
		 createParTable(subInfo,par);
	   }
	delTable();//删除参考意见表
	
	
}

/**
 * 删除参考意见表的按钮响应事件
 * */
function delTable(){
	delBtn=document.getElementsByName("delBtn");
	for(let i=0;i<delBtn.length;i++){
		delBtn[i].onclick=function(){
		 
		 var div=this.parentNode.parentNode;
		 var confir=confirm("确定删除？");
		 if(confir){
		 	if(i<paraArr.length){
		 		getDelParInter(i);
		 	}
		 	div.parentNode.removeChild(div);
		 }
		}
	}
}

/**
 * 添加参考意见表的按钮响应事件
 * */
function addTable(){
	if(parLen<26){
	 var name=['a','b','c','d','e','f','g','h','i','j','k',
	 'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
	 var par=new Array();
	 var scoreHead_1="scoreHead_"+parLen;
	 var scoreRear_1="scoreRear_"+parLen;
	 var male_1="male_"+parLen;
	 var female_1="female_"+parLen;
	 var refSex_1="refSex_"+parLen;
	 var refAge_1="refAge_"+parLen;
	 var ageHead_1="ageHead_"+parLen;
	 var ageRear_1="ageRear_"+parLen;
	 var paraInfo_1="paraInfo_"+parLen;
	 var index="adv"+parLen;
	 var id1=name[parLen]+1;
	 var id2=name[parLen]+2;
	 var id3=name[parLen]+3;
	 var id4=name[parLen]+4;
	 var id5=name[parLen]+5;
	 var subInfo=[index,id1,id2,id3,id4,id5,scoreHead_1,scoreRear_1,refSex_1,male_1,
	 female_1,refAge_1,ageHead_1,ageRear_1,paraInfo_1];
     createParTable(subInfo,par);
     parLen++;
     delTable();
	 onclickTable();
	 onMale();
	 onFemale();
	}else{
		alert("添加已达上限")
	}
   
}

/**
 * 创建参考意见表
 * */
function createParTable(subInfo,par){
	var fourth=document.getElementById("fourthDiv");
	if(par.referenceSex==1){
		check1=true;
		check2=false;
		check3=true;
	}else if(par.referenceSex==0){
		check1=false;
		check2=true;
		check3=true;
	}else{
		check1=false;
		check2=false;
		check3=false;
	}
	if(par.referenceBeginAge>=0){
		check4=true;
	}else {
		check4=false;
	}
	$('<div/>',{id:subInfo[0],name:'table',
	style:"display: flex;flex-direction: column;width: 740px;height: 300px;margin-top: 20px;"}).appendTo(fourth);//二级标签
	
	$('<div/>',{id:subInfo[1]}).appendTo($("#"+subInfo[0]));//三级标签
	$('<div/>',{id:subInfo[2],style:"margin-top: 15px;"}).appendTo($("#"+subInfo[0]));//三级标签
	$('<div/>',{id:subInfo[3],style:"margin-top: 20px;"}).appendTo($("#"+subInfo[0]));//三级标签
	$('<div/>',{id:subInfo[4],style:"margin-top: 20px;"}).appendTo($("#"+subInfo[0]));//三级标签
	$('<div/>',{id:subInfo[5],
	style:"width:770px;height:1px;border: 1px solid black;margin-top:20px;margin-left: -10px;"}).appendTo($("#"+subInfo[0]));//三级标签
/*-------------------------------------------------------------------------------------------*/
	$('<span/>',{style:"margin-left: 15px;margin-top: 20px;",
	text:'得分头：'
	}).appendTo($("#"+subInfo[1]));//1：四级标签
	
	$('<input/>',{id:subInfo[6],style:"margin-left: 0px;margin-top: 20px;width: 100px;",
	value:par.referenceBeginScore,
	placeholder:'必须为整数',
	}).appendTo($("#"+subInfo[1]));//1：四级标签
	
	$('<span/>',{style:"margin-left: 10px;margin-top: 20px;",
	text:'得分尾：'
	}).appendTo($("#"+subInfo[1]));//1：四级标签
	
    $('<input/>',{id:subInfo[7],style:"margin-left: 0px;margin-top: 20px;width: 100px;",
    value:par.referenceEndScore,
    placeholder:'必须为整数',
	}).appendTo($("#"+subInfo[1]));//1：四级标签
/*----------------------------------------------------------------------------------------*/	
	$('<input/>',{id:subInfo[8],type:"checkbox",
	style:"margin-left: 18px;margin-top:5px;",
	name:'refSex',
	checked:check3
	}).appendTo($("#"+subInfo[2]));//2：四级标签
	
	$('<span/>',{style:"margin-top: 0px;margin-left: 0px;font-size: 15px;",
	text:'性别相关'
	}).appendTo($("#"+subInfo[2]));//2：四级标签  
	
	$('<span/>',{style:"margin-left: 40px;font-size: 15px;",
	text:'男'
	}).appendTo($("#"+subInfo[2]));//2：四级标签  	
	
    $('<input/>',{id:subInfo[9],type:"radio",
    class:'male',
    name:subInfo[9],
    checked:check1,
	style:"margin-left: 0px;",
	}).appendTo($("#"+subInfo[2]));//2：四级标签   
	
    $('<span/>',{style:"margin-left: 20px;font-size: 15px;",
	text:'女'
	}).appendTo($("#"+subInfo[2]));//2：四级标签  	
	
    $('<input/>',{id:subInfo[10],type:"radio",
     class:'female',
    name:subInfo[10],
    checked:check2,
	style:"margin-left: 0px;",
	}).appendTo($("#"+subInfo[2]));//2：四级标签   

    $('<input/>',{id:subInfo[11],type:"checkbox",
    name:'refAge',
	style:"margin-left: 50px;margin-top: 5px;",
	checked:check4
	}).appendTo($("#"+subInfo[2]));//2：四级标签  
	
    $('<span/>',{style:"font-size:15px;",
	text:'年龄相关'
	}).appendTo($("#"+subInfo[2]));//2：四级标签 
	
	$('<span/>',{style:"font-size:15px;",
	text:'年龄头:'
	}).appendTo($("#"+subInfo[2]));//2：四级标签 

	
    $('<input/>',{id:subInfo[12],
	style:"margin-left: 0px;margin-top: 0px;width: 80px;",
	placeholder:'必须为整数',
	value:par.referenceBeginAge
	}).appendTo($("#"+subInfo[2]));//2：四级标签   
	
	$('<span/>',{style:"margin-left: 0px;margin-top: 0px;",
	text:'年龄尾：'
	}).appendTo($("#"+subInfo[2]));//2：四级标签   
	
	$('<input/>',{id:subInfo[13],
	style:"margin-left: 0px;margin-top: 0px;width: 80px;",
	placeholder:'必须为整数',
	value:par.referenceEndAge
	}).appendTo($("#"+subInfo[2]));//2：四级标签   
/*----------------------------------------------------------------------------*/
	 
	$('<span/>',{style:"margin-left:15px;margin-top: 0px;",
	text:'参考意见:'
	}).appendTo($("#"+subInfo[3]));//3：四级标签   
	
	$('<textarea/>',{id:subInfo[14],
	style:"margin-top:10px;margin-left: 16px;width: 700px;height: 100px;resize: none;",
	placeholder:par.referenceSuggestion
	}).appendTo($("#"+subInfo[3]));//3：四级标签  
    
    $('<button/>',{name:'saveBtn',
    style:"margin-left:20px;width: 80px;height: 30px;border: 1px solid black;border-radius: 3px;",
	text:"保存信息",
	}).appendTo($("#"+subInfo[4]));//3：四级标签 

	$('<button/>',{name:'delBtn',
	style:"margin-left:60px;width: 80px;height: 30px;border: 1px solid black;border-radius: 3px;",
	text:"删除该表",
	}).appendTo($("#"+subInfo[4]));//3：四级标签   
}

/**
 * 获取参考信息接口
 * */
function getParaInfo(){
	var factor_id=facArr[nowIndex].factorId;
	console.log(factor_id);
	$.ajax({
    type : "POST",
    url : "http://47.105.136.92/MentalTest/QueryReferenceByFactor",
    dataType : "json",
    data : {
       factor_id :factor_id,
    },
    success : function(data) {
        console.log(data.Array);
    	if(data.Array!=null){
    	paraArr=data.Array;
        initialParaInfo(0);//初始化参考信息
    	onMale();//按钮响应事件
    	onFemale();
    	onclickTable();//响应点击参考表的响应事件
    	parLen=paraArr.length;//当前表的长度
    	}else{
    	paraArr=null;
    	}
    },  
    fail : function(){
    	console.log("失败")
    }
      });
}

/**
 * 获取删除参考意见表的接口
 * */
function getDelParInter(i){
	var reference_id=paraArr[i].referenceId;
	console.log(reference_id);
	$.ajax({
    type : "POST",
    url : "http://47.105.136.92/MentalTest/DeleteReference",
    dataType : "json",
    data : {
       reference_id :reference_id,
    },
    success : function(data) {
    	console.log(data);
    },  	
      });
}

/**
 * 点击保存参考意见表响应事件
 * */
function onclickTable(){
	 var saveBtn=document.getElementsByName("saveBtn");
	for(let i=0;i<saveBtn.length;i++){
		tabIndex=i;
		saveBtn[i].onclick=function(){
		var scoreHead_1="scoreHead_"+i;
	    var scoreRear_1="scoreRear_"+i;
	    var male_1="male_"+i;
	    var female_1="female_"+i;
	    var refSex_1="refSex_"+i;
	    var refAge_1="refAge_"+i;
	    var ageHead_1="ageHead_"+i;
	    var ageRear_1="ageRear_"+i;
	    var paraInfo_1="paraInfo_"+i;
	    var Info=[scoreHead_1,scoreRear_1,male_1,female_1,refSex_1,
	    refAge_1,ageHead_1,ageRear_1,paraInfo_1];
	    getSaveParInfo(Info);
		}
	}
}



/**
 * 性别选择和相关性事件响应
 * */
function onMale(){
	var male=document.getElementsByClassName("male");

	for(let i=0;i<male.length;i++){
		male[i].onclick=function(){
			var female_1="female_"+i;
			$('#'+female_1).attr('checked',false);
		}
	}
}

function onFemale(){
	var female=document.getElementsByClassName("female");
	for(let i=0;i<female.length;i++){
		female[i].onclick=function(){
			var male_1="male_"+i;
			$("#"+male_1).attr('checked',false); 
		}
	}
}