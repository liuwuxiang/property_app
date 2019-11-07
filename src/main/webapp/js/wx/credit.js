var storage = window.localStorage;

window.onload = function () {
  var credit_date = document.getElementById("credit_date").value;
  var screentWidth = self.window.innerWidth;
  var canvas = document.getElementById('canvas');
  canvas.width=screentWidth;
  var ctx = canvas.getContext('2d');
  var cWidth = canvas.width;
  var cHeight = canvas.height;
  var score = canvas.attributes['data-score'].value;
  var stage = ['较差', '中等', '良好', '优秀', '极好'];
  var radius = 150;
  var deg0 = Math.PI / 9;
  var deg1 = Math.PI * 11 / 45;

  if (score >= 0 || score <= 1000) {
    var dot = new Dot(),
        dotSpeed = 0.03,
        textSpeed = Math.round(dotSpeed * 100 / deg1),
        angle = 0,
        credit = 0;

    (function drawFrame() {

      ctx.save();
      ctx.clearRect(0, 0, cWidth, cHeight);
      ctx.translate(cWidth / 2, cHeight / 2);
      ctx.rotate(8 * deg0);

      dot.x = radius * Math.cos(angle);
      dot.y = radius * Math.sin(angle);

      var aim = (score - 0) * deg1 / 200;
      if (angle < aim) {
        angle += dotSpeed;
      }
      dot.draw(ctx);

      if (credit < score - textSpeed) {
        credit += textSpeed;
      } else if (credit >= score - textSpeed && credit < score) {
        credit += 1;
      }
      text(credit);

      ctx.save();
      ctx.beginPath();
      ctx.lineWidth = 3;
      ctx.strokeStyle = 'rgba(255, 255, 255, .5)';
      ctx.arc(0, 0, radius, 0, angle, false);
      ctx.stroke();
      ctx.restore();

      window.requestAnimationFrame(drawFrame);
        // alert(aim);

      ctx.save(); //中间刻度层
      ctx.beginPath();
      ctx.strokeStyle = 'rgba(255, 255, 255, .2)';
      ctx.lineWidth = 10;
      ctx.arc(0, 0, 135, 0, 11 * deg0, false);
      ctx.stroke();
      ctx.restore();

      ctx.save(); // 刻度线
      for (var i = 0; i < 6; i++) {
        ctx.beginPath();
        ctx.lineWidth = 2;
        ctx.strokeStyle = 'rgba(255, 255, 255, .3)';
        ctx.moveTo(140, 0);
        ctx.lineTo(130, 0);
        ctx.stroke();
        ctx.rotate(deg1);
      }
      ctx.restore();

      ctx.save(); // 细分刻度线
      for (i = 0; i < 25; i++) {
        if (i % 5 !== 0){
          ctx.beginPath();
          ctx.lineWidth = 2;
          ctx.strokeStyle = 'rgba(255, 255, 255, .1)';
          ctx.moveTo(140, 0);
          ctx.lineTo(133, 0);
          ctx.stroke();
        }
        ctx.rotate(deg1 / 5);
      }
      ctx.restore();

      ctx.save(); //信用分数
      ctx.rotate(Math.PI / 2);
      for (i = 0; i < 6; i++) {
        ctx.fillStyle = 'rgba(255, 255, 255, .4)';
        ctx.font = '10px Microsoft yahei';
        ctx.textAlign = 'center';
        ctx.fillText(0 + 200 * i, 0, -115);
        ctx.rotate(deg1);
      }
      ctx.restore();

      ctx.save(); //分数段
      // ctx.rotate(Math.PI / 2 + deg0);
      // for (i = 0; i < 5; i++) {
      //   ctx.fillStyle = 'rgba(255, 255, 255, .4)';
      //   ctx.font = '10px Microsoft yahei';
      //   ctx.textAlign = 'center';
      //   ctx.fillText(stage[i], 5, -115);
      //   ctx.rotate(deg1);
      // }
      // ctx.restore();

      // ctx.save(); //信用阶段及评估时间文字
      ctx.rotate(10 * deg0);
      ctx.fillStyle = '#fff';
      ctx.font = '28px Microsoft yahei';
      ctx.textAlign = 'center';
      if (score <= 500) {
        ctx.fillText('信用较差', 0 , 40);
      } else if (score > 500 && score <= 650) {
        ctx.fillText('信用中等', 0 , 40);
      } else if (score > 650 && score <= 800) {
        ctx.fillText('信用良好', 0 , 40);
      } else if (score > 800 && score <= 950) {
        ctx.fillText('信用优秀', 0 , 40);
      } else if (score >= 1000) {
        ctx.fillText('信用极好', 0 , 40);
      }

      ctx.fillStyle = '#80cbfa';
      ctx.font = '14px Microsoft yahei';
      ctx.fillText('评估时间：'+credit_date, 0, 60);

      ctx.fillStyle = '#7ec5f9';
      ctx.font = '14px Microsoft yahei';
      ctx.fillText('BETA', 0, -60);
      ctx.restore();


      // ctx.save(); //最外层轨道
      ctx.beginPath();
      ctx.strokeStyle = 'rgba(255, 255, 255, .4)';
      ctx.lineWidth = 3;
      ctx.arc(0, 0, radius, 0, 11 * deg0, false);
      ctx.stroke();
      ctx.restore();

    })();
  } else {
    alert('信用分数区间：0~1000');
  }

  function Dot() {
    this.x = 0;
    this.y = 0;
    this.draw = function (ctx) {
      ctx.save();
      ctx.beginPath();
      ctx.fillStyle = 'rgba(255, 255, 255, .7)';
      ctx.arc(this.x, this.y, 3, 0, Math.PI * 2, false);
      ctx.fill();
      ctx.restore();
    };
  }

  function text(process) {
    ctx.save();
    ctx.rotate(10 * deg0);
    ctx.fillStyle = '#000';
    ctx.font = '80px Microsoft yahei';
    ctx.textAlign = 'center';
    ctx.textBaseLine = 'top';
    ctx.fillText(process, 0 ,10);
    ctx.restore();
  }


    getRechargeDetail();
};


//获取充值明细
function getRechargeDetail() {
    toast(2,"打开loading");
    publicnull_tip("暂无评级",1);
    $("#consumption_detail_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserCreditRecord",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                var list = data.data.list;
                if (list.length <= 0){
                    toast(3,"关闭Loading");
                    publicnull_tip("暂无评级",1);
                }
                else{
                    toast(3,"关闭Loading");
                    publicnull_tip("暂无评级",0);
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "";
                        if (obj.type == 1){
                            html = "<li class=\"item\">"+
                                "<div class=\"left\">"+
                                "<span class=\"name\">"+obj.credit_name+"</span>"+
                                "<span class=\"time\">"+obj.credit_date+"</span>"+
                                "</div>"+
                                "<div class=\"right\">"+
                                "<span class=\"num down\">-"+obj.credit_value+"</span>"+
                                "</div>"+
                                "</li>";
                        }
                        else{
                            html = "<li class=\"item\">"+
                                "<div class=\"left\">"+
                                "<span class=\"name\">"+obj.credit_name+"</span>"+
                                "<span class=\"time\">"+obj.credit_date+"</span>"+
                                "</div>"+
                                "<div class=\"right\">"+
                                "<span class=\"num down\">+"+obj.credit_value+"</span>"+
                                "</div>"+
                                "</li>";
                        }
                        $("#consumption_detail_ul").append(html);
                    }
                }
            }
            else{
                toast(3,"关闭Loading");
                publicnull_tip(data.msg,1);
            }
        },
    });
}


/*
* 提示修改
* */
function publicnull_tip(content,state) {
    var publicnull_tip = document.getElementById("publicnull_tip");
    if (state == 0){
        publicnull_tip.style.display = "none";
        document.getElementById("tip_div").style.minHeight = "50%";
    }
    else{
        document.getElementById("request_tip").innerText = content;
        publicnull_tip.style.display = "block";
        document.getElementById("tip_div").style.minHeight = "0%";

    }
}