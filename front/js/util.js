
//用于获取url种name参数的值，不存在返回false
function getQueryVariable(name)
{
    name = name.replace(/[]/,"\[").replace(/[]/,"\[").replace(/[]/,"\\\]");
    var regexS = "[\\?&]"+name+"=([^&#]*)";
    var regex = new RegExp( regexS );
    var results = regex.exec(window.parent.location.href);
    if( results == null ) 
        return ""; 
    else {
        return decodeURI(results[1]);
    }
}



//问题页面换页
function changeQuestionPage(page, path){
    $.ajax({
        url: path,
        type: "post", 
        data:JSON.stringify(page),
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            page = data.data;
            console.info(page);
            updateList(page);
        }
    });
}

//回复页面换页
function changeQuestionPage(page, path){
    $.ajax({
        url: path,
        type: "post", 
        data:JSON.stringify(page),
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            page = data.data;
            console.info(page);
            updateList(page);
        }
    });
}

//将数据存储到缓存
Storage.prototype.setExpire=(key, value, expire) =>{
    let obj={
        data:value,
        time:Date.now(),
        expire:expire
    };
    localStorage.setItem(key,JSON.stringify(obj));
}

//从缓存中读取数据
Storage.prototype.getExpire= key =>{
    let val =localStorage.getItem(key);
    if(!val){
        return val;
    }
    val =JSON.parse(val);
        if(Date.now()-val.time>val.expire){
        localStorage.removeItem(key);
        return null;
    }
    return val.data;
}


function initInfo(userVO){//对个人信息进行赋值
    //姓名
    var nameDD = document.getElementById("infoName");
    nameDD.innerHTML = userVO.user.name;
    //账号（学号）
    var accountDD = document.getElementById("infoAccount");
    accountDD.innerHTML = userVO.user.account;
    //等级
    var levelDD = document.getElementById("infoLevel");
    levelDD.innerHTML = userVO.accountData.level;
    //积分
    var scoreDD = document.getElementById("infoScore");
    scoreDD.innerHTML = userVO.accountData.score;
    //提问数
    var quesNumDD = document.getElementById("infoQuesNum");
    quesNumDD.innerHTML = userVO.accountData.questionNum;
    //回复数
    var respNumDD = document.getElementById("infoRespNum");
    respNumDD.innerHTML = userVO.accountData.responseNum;
    //关注数
    var focusNumDD = document.getElementById("infoFocusNum");
    focusNumDD.innerHTML = userVO.accountData.focusNum;
}

//获取格式为yyyy-MM-dd HH-mm-ss格式的时间字符串
function getTimeString(date){
    var year = date.getFullYear();

    var month = date.getMonth() + 1;
    if(month < 10){
        month = "0" + month;
    }

    var strDate = date.getDate();
    if(strDate < 10){
        strDate = "0" + strDate;
    }

    var hour = date.getHours();
    if(hour < 10){
        hour = "0" + hour;
    }

    var min = date.getMinutes(); 
    if(min < 10){
        min = "0" + min;
    }
    
    var sec = date.getSeconds(); 
    if(sec < 10){
        sec = "0" + sec;
    }

    return "" + year + "-" + month + "-" + strDate + " " + hour + ":" + min + ":" + sec;
}

//创建问题
function postQuestion(){
    var title =  $.trim($("#inputQue").val());
    var content = $.trim($("#describeQue").val());
    //创建时间
    var time = new Date();
    var timeString = getTimeString(time);
    var question={
        "title":title,
        "content":content,
        "question":{
            "authorId":userVO.user.id,
            "createTime":timeString
        }
    }
    console.info(question);
    $.ajax({
        url: "http://localhost:8888/question",
        type: "post", 
        data:JSON.stringify(question),
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            console.info(data);
            alert("创建成功");
        }
    });
    //将输入内容清空
    $("#inputQue").innerHTML = "";
    $("#describeQue").innerHTML = "";
    location.replace(document.referrer);
}


//判断后台是否有临时板块（有则显示，无则隐藏）
function getBlock(){
    //获取临时板块的元素
    var tempBlock = document.getElementById("tempBlock");
    $.ajax({
        url: "http://localhost:8888/block",
        type: "get", 
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            if(data.code == 200){
                block = data.data;
                //有临时板块
                //展示临时板块按钮
                tempBlock.removeAttribute("style");
                var blockLink = document.createElement("a");
                blockLink.innerHTML = block.blockName;
                var href = "search.html?keyWord=" +  block.keyWord;
                blockLink.setAttribute("href",href);
                tempBlock.appendChild(blockLink);
                //console.info(block);
            }else if(data.code == 201){
                //没有临时板块
                //隐藏临时板块按钮
                tempBlock.setAttribute("style","display:none");
            }
        }
    });
}

//更新问题列表
function updateList(p){
    page = p;
    var list = page.questions;
    list = Array.prototype.slice.call(list);
    for(var i = 0; i < list.length; i++){
        //创建存放单个问题的blockquote
        var questionBlock = document.createElement("blockquote");
        //创建存放问题标题的a标签
        var title = document.createElement("a");
        title.innerHTML = "标题：" + list[i].title;//问题标题
        title.setAttribute("href", "questionIndex.html?id=" + list[i].question.id);//问题标题对应的详情页面
        //创建存放问题描述的p标签
        var content = document.createElement("small");
        content.innerHTML = "问题描述：" + list[i].content;//问题描述
        questionBlock.appendChild(title);
        questionBlock.appendChild(content);
        questionList.appendChild(questionBlock);
        
        //更新分页按钮
        updatePageButtons();
    }
}

//将用户信息保存到缓存中(时间为30分钟)
function updateUser(userVO){
    localStorage.setExpire("userVO",userVO,1000*60*30);
}



//搜索框搜素函数
function search(){
    //搜索框输入内容
    var searchContent = document.getElementById("searchContent");
    var content = $.trim($("#searchContent").val());
    window.location.href="search.html?keyWord=" + content;
}

var userVO;
//获取当前登录用户
userVO = localStorage.getExpire("userVO");
if(userVO != null){
    updateUser(userVO);
}

