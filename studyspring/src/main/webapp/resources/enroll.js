/**
 * 
 */
function inputBirth(date){
	var birthText = document.getElementById("birthText");
	var birth = document.getElementById("birth");
	
	if (date.length == 4 || date.length == 7) {
		birthText.value += "/";
	}
	
	birth.value = birthText.value;
}