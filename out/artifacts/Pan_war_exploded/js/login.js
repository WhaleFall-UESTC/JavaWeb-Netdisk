const cards=document.querySelectorAll('.card');
const btns=document.querySelectorAll('.js-btn');
btns.forEach(btn=>{btn.addEventListener('click',on_btn_click,true);btn.addEventListener('touch',on_btn_click,true);});
function on_btn_click(e){
    const nextID=e.currentTarget.getAttribute('data-target');
    const next=document.getElementById(nextID);
    if(!next)return;
    bg_change(nextID);
    view_change(next);
    return false;
}
function bg_change(next){document.body.className='';document.body.classList.add('is-'+next);}
function view_change(next){cards.forEach(card=>{card.classList.remove('is-show');});next.classList.add('is-show');}



// document.addEventListener('DOMContentLoaded', function() {
//     var submitButton = document.getElementById('encrypt');
//     submitButton.addEventListener('click', async function() {
//         await encryptPassword('passwd', 'password');
//         document.getElementById('registerForm').submit();
//     });
// });
//
// document.addEventListener('DOMContentLoaded', function() {
//     var submitButton = document.getElementById('encrypt1');
//     submitButton.addEventListener('click', async function() {
//         alert('login');
//         await encryptPassword('passwd1', 'password1');
//         document.getElementById('loginForm').submit();
//     });
// });

async function encryptPassword(in_id, out_id) {
    console.log(in_id, out_id);
    const password = document.getElementById(in_id);
    const encoded = new TextEncoder().encode(password.value);
    const hashBuffer = await crypto.subtle.digest('SHA-256', encoded);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
    console.log(hashHex);
    document.getElementById(out_id).value = hashHex;
    console.log(password);
}