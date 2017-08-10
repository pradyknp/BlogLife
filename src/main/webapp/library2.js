window.addEventListener("load", function() {
	document.getElementById("addLink").addEventListener("click", function() {
		window.document.getElementById("addForm").style.display = "block";
		window.document.getElementById("findForm").style.display = "none";
		window.document.getElementById("findResults").style.display = "none";
	});
	document.getElementById("findLink").addEventListener("click", function() {
		window.document.getElementById("addForm").style.display = "none";
		window.document.getElementById("findForm").style.display = "block";
		window.document.getElementById("findResults").style.display = "block";
	});
	document.getElementById("findButton").addEventListener("click", function() {
		var isbn = document.getElementById("key").value;
		var url = "public/book/" + isbn;
		var xhr = new XMLHttpRequest();
		xhr.open("get", url, true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var book = JSON.parse(xhr.responseText);
				document.getElementById("bookTitle").innerHTML = book.title;
			}
		};
		xhr.send();
	});
});