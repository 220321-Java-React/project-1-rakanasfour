const url = "http://localhost:3000"; //putting our base URL in a variable for cleaner code below
//eventually, we'll use this in fetch requests and make calls to our server by appending endpoints

//add an event listener to give our buttons functionality (using DOM selection)
//"When the getEmployeeButton gets clicked, execute the getEmployees function"
document.getElementById("getRequestButton").addEventListener("click", getRequests);

//"When the loginButton gets clicked, execute the loginFunction"
document.getElementById("loginButton").addEventListener("click", loginFunction);

//document.getElementById("addrequest").addEventListener("click",addRequest);
//getEmployees is an async function which has a fetch request to get employees from our server
//remember, async makes a function return a Promise (which fetch requests return)
async function getRequests() {

    //we will send a fetch request to get out employee data
    //by default, fetch requests send GET requests (see how to send others like POST below)
    let response = await fetch(url + "/requests", {credentials: "include"});

    //log the response in the console just to see the response object (good for debugging)
    console.log(response);

    //control flow on the status code (which tells us whether the request was successful)
    //we can access the status code (as well as other stuff) through our response variable
    if(response.status === 200) { //"if the response has a status code of 200..."

        //parse the JSON we get back into a JS object
        //.json() is the JS method that takes json and turns it into a JS object
        let data = await response.json();

        //log the actual employee data that's been parsed from JSON (good for debugging)
        console.log(data);

        //For every Employee object we get back from our fetch request, put it in the table
        for(let request of data){

            //create a table row
            let row = document.createElement("tr");

            //create a data cell for each employee field
            let cell = document.createElement("td");
            //fill the cell with the appropriate employee data
            cell.innerHTML = request.reimb_id;
            //add the td element (data cell) to the tr element (table row)
            row.appendChild(cell);

            //we do this^^^^ for every column in employees

            let cell2 = document.createElement("td");
            cell2.innerHTML = request.reimb_amount;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = request.reimb_submitted;
            row.appendChild(cell3);

           
            let cell4 = document.createElement("td");
            cell4.innerHTML = request.user.ers_username;
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell4.innerHTML = request.status.reimb_status;
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            cell4.innerHTML = request.type.reimb_type;
            row.appendChild(cell6);

            //append the tr (which we called "row") to the table body (tbody)
            //a new row will be appended FOR every employee that got returned in the fetch()
            document.getElementById("requestBody").appendChild(row);

        }
        //so here, for every employee object, we create a new row, fill it with data, add it to table

    } else {
        //alert causes a popup!!
        alert("uh oh your session is inactive. Maybe not logged in? :/");
    }


}

async function addRequest(){
    let rid = document.getElementById("reimb_id").value;
    let amount = document.getElementById("reimb_amount").value;
    let submitted = document.getElementById("reimb_submitted").value;
    let author_fk = document.getElementById("reimb_author_fk").value;
    let status_id_fk = document.getElementById("reimb_status__id_fk").value;
    let type_id_fk = document.getElementById("reimb_type_id_fk").value;

    let requestObject= {
        reimb_id:id,
        reimb_amount:amount,
        reimb_submitted:submitted,
        reimb_author_fk:author_fk,
        reimb_status__id_fk : status_id_fk,
        reimb_type_id_fk:type_id_fk
    }
    console.log(RequestObject);

    //fetch request to the server
    //rememeber, the second parameter in a fetch is for configuring our fetch request
    //fetch sends a GET by default, but we need a POST, as well as some other configs
    let response = await fetch(url+"/requests", {

        method: "POST", //send a POST request (would be a GET if we didn't specify...)
        body: JSON.stringify(RequestObject), //turning our user object into JSON to send to the server
        credentials: "include"
        //this last line will ensure that the cookie is captured (so that we can have a session)
        //future fetches after login will require this "include" value 
})
}
//log the response status code, useful for debugs
console.log(response.status);


//control flow based on successful/unsuccessful login
if(response.status === 202){

     //converting from json to JS
     let data = await response.json();
    //wipe our login row and welcome the user
    document.getElementById("request_row").innerText="Welcome " + data.first_name + "!!";

    //THIS IS PROBABLY WHERE YOUR REDIRECT WOULD BE IF USING MULTIPLE HTML PAGES
    //don't be intimidated, it's an easy google :)

} else {
    document.getElementById("requestHead").innerText="Login failed! Try Again";
    document.getElementById("requestHead").style.color = "red";
}

//this function will send the user-inputted login credentials to our server
async function loginFunction(){

//gather the user inputs from the login inputs
//when the login button is clicked, the value from username and password will be put into variables
let usern = document.getElementById("username").value;
let userp = document.getElementById("password").value;

//we want to send the user/pass as JSON, so we need a JS object first.
let user = {
    username:usern,
    password:userp
}
//This object should reflect the LoginDTO in our Java... This is the data we want to transfer

//for debugging purposes, print out the user object to the console
console.log(user);

//fetch request to the server
//rememeber, the second parameter in a fetch is for configuring our fetch request
//fetch sends a GET by default, but we need a POST, as well as some other configs
let response = await fetch(url+"/login", {

    method: "POST", //send a POST request (would be a GET if we didn't specify...)
    body: JSON.stringify(user), //turning our user object into JSON to send to the server
    credentials: "include"
    //this last line will ensure that the cookie is captured (so that we can have a session)
    //future fetches after login will require this "include" value 
})

//log the response status code, useful for debugs
console.log(response.status);


//control flow based on successful/unsuccessful login
if(response.status === 202){

     //converting from json to JS
     let data = await response.json();
    //wipe our login row and welcome the user
    document.getElementById("loginRow").innerText="it's going to print the the request amont " + data.reimb_amount + "!!";

    //THIS IS PROBABLY WHERE YOUR REDIRECT WOULD BE IF USING MULTIPLE HTML PAGES
    //don't be intimidated, it's an easy google :)

} else {
    document.getElementById("welcomeHead").innerText="Login failed! Try Again";
    document.getElementById("welcomeHead").style.color = "red";
}

}