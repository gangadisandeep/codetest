Q. Is there anything you’d do to further secure this application or the systems running it?

Ans. 

Create a deny all (for all internal/external IP addresses to all the ports on the host) firewall 
on the mysql database host. Will Allow access to only whitelisted IP addresses on port 3306(mysql 
port). In this case this IP address will be of the application server sitting on the other host.

Deploy the host machines in a private cloud. This will allow us in keeping the communication 
between db and application server non-secure (performance reason), while the API end point 
has to be secure.


Q. How will you know if a security compromise has occurred?

Ans. We have to keep some checks to ensure that account security is not compromised. Below
are some ideas-

1. If multiple login requests for the same account are coming from different geographies, 
it might be a sign of hacking attempt.

2. We should block the account if the number of unsuccessful login attempts go beyond 
certain limit.

3. We need to have more intelligence built on frontend also to track hacking attempts such as 
instances of copying and pasting username/password instead of typing it. Also if the typing is
not following a rythm it could be an indication that someone is copying or following the password. 