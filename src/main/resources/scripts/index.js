


var t = document.getElementsByClassName('fold-button');

for(var i = t.length-1 ; i >= 0 ; i--) {
     
    t[i].addEventListener('click', function(e) {
        element_style = document.getElementById(e.target.id + '-table').style;
          
        if(element_style.display != 'none') {
            element_style.display = 'none';
        } else if(element_style.display == 'none') {
            element_style.display = '';
        }
    });

}