CREATE TABLE ers_reimbursement(

reim_id int serial PRIMARY;
reimb_amount int;
reimb_submitted date;
reimb_author int;
reimb_resolver int;
reimb_status_id int;
reimb_type_id int REFERENCES ;


);
SELECT * FROM ers_reimbursement;





CREATE TABLE ers_users(
ers_users_id int serial primary;
ers_username varchar(50);
ers_password varchar(50);
user_first_name varchar(100);
user_last_name varchar(100);
user_email varchar(150);
user_role_id int;


);




CREATE TABLE ers_reimbursement_status(

reimb_status_id int serial PRIMARY;
reimb_status varchar(10);


);
CREATE TABLE ers_reimbursement_type(

reimb_type_id int serial PRIMARY;
reimb_type varchar(10);

);

CREATE TABLE ers_user_roles(
ers_user_role_id int;
user_role varchar(10);


);


