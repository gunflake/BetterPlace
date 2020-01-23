function openNav() {
    document.getElementById("mySidenav").style.width = "350px";
    document.getElementById("main").style.marginRight = "0";

    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginRight= "0"; // div id="main" 지정 아직 못함, 이미지 파일 음영 불가

    document.body.style.backgroundColor = "white";
}