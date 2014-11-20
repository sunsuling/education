// 验证是否是正数
function validate(sDouble){
	var re = /^[1-9]\d*$/
	return re.test(sDouble) 
}
// 文本框只能输入数字
function IsNum(e) {           
	var k = window.event ? e.keyCode : e.which;     
	if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {   
		
	} else {              
		if (window.event) {        
			window.event.returnValue = false;        
			}else {  
				e.preventDefault();        
				}           
		}  
}


/**
 * 编码验证 非汉字
 * @param str
 * @returns {Boolean}
 */
function validateCode(str) {
	if((/[\u4e00-\u9fa5]+/).test(str)){     	    
	     return false; 
	}  
	return true;
}

//时间格式验证       YYYY-MM-DD
function validateDate(date)
{   
     var result = date.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
     if(result==null && date != '')
     {
         return false;
     }else{
    	 return true;
     }
     
}


//+---------------------------------------------------
//| 日期时间检查
//| 格式为：MM:SS
//+---------------------------------------------------
function validateOnlyTime(date)
{

         var reg = /^(\d+)-(\d{2})-(\d{2}) (\d{2}):(\d{2})$/;
         var str = '1900-01-01 '+date ;

         var r = str.match(reg);

         if(r==null)return false;
         r[2]=r[2]-1;
         var d= new Date(r[1],r[2],r[3],r[4],r[5]);
         if(d.getFullYear()!=r[1])return false;
         if(d.getMonth()!=r[2])return false;
         if(d.getDate()!=r[3])return false;
         if(d.getHours()!=r[4])return false;
         if(d.getMinutes()!=r[5])return false;

         return true;

}
//YYYY-MM-DD HH:MM
function validateDateTime(date)
{
     var reg = /^(\d+)-(\d{2})-(\d{2}) (\d{2}):(\d{2})$/;
      var r = date.match(reg);
      if(r==null)return false;
      r[2]=r[2]-1;
      var d= new Date(r[1],r[2],r[3],r[4],r[5]);
      if(d.getFullYear()!=r[1])return false;
      if(d.getMonth()!=r[2])return false;
      if(d.getDate()!=r[3])return false;
      if(d.getHours()!=r[4])return false;
      if(d.getMinutes()!=r[5])return false;

      return true;

}


