/**
 * 上传文件
 * @param role 角色
 * @param url 上传目标URL
 * @param check 函数, 校验文件
 */
function uploadFile(role, url, check) {
  var fileObj = document.getElementById("selectFile").files[0]; // js 获取文件对象
  if(check(fileObj) === 1){
    return 0;
  }

  var form = new FormData();
  form.append("file", fileObj);
  var xhr = new XMLHttpRequest();
  xhr.open("post", url, true);
  xhr.setRequestHeader('Authorization','Mythos '+checkRole(role));
  xhr.onload = function (data) {
    layer.msg('文件上传完成'+JSON.stringify(data));
    // console.log('上传成功'+JSON.stringify(data));
  };
  xhr.onerror=function(data){
    layer.msg('上传发生故障,请重试'+JSON.stringify(data));
    // console.log('上传发生故障,请重试'+JSON.stringify(data));
  };
  // xhr.onloadstart=function(data){
  //   // layer.msg('开始上传'+JSON.stringify(data));
  //   console.log('开始上传'+JSON.stringify(data));
  // };
  xhr.onloadend=function(){
    var responses = JSON.parse(xhr.responseText);
    if(responses.code === '202'){
      layer.msg('文件格式错误,请选择正确的文件')
    }else if(responses.code === '203'){
      layer.msg('文件为空,请选择正确的文件')
    }else if(responses.code !== '0'){
      layer.msg('文件上传失败,请重试')
    }else{
      layer.msg("文件上传成功");
    }
  };

  xhr.upload.addEventListener("progress", progressFunction, false);
  xhr.send(form);
}

function progressFunction(evt) {
  var progressBar = document.getElementById("progressBar");
  var percentageDiv = document.getElementById("percentage");
  if (evt.lengthComputable) {
    progressBar.max = evt.total;
    progressBar.value = evt.loaded;
    percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100) + "%";
  }
}