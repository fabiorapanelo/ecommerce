# ecommerce

My goal is create a ecommerce using JEE that can be consumed by Web App, REST and SOAP.

So far I have used the following technologies:

- JAX-RS - Jersey
- JAX-WS
- OAuth 2 - Apache OAuth (Integration with Google for login in the webapp and Authentication server for my exposed APIs)
- Hibernate (I will migrate to JPA)
- JAAS

Before run the project, configure jaas.config:

Double-click the Tomcat instance on servers tab
Click "Open launch configuration"
In the "Arguments" tab, there is a VM arguments input text.
Append your parameter: -Djava.security.auth.login.config="", i.e:

-Djava.security.auth.login.config="D:\tomcat\7.0.50"\conf\jaas.config"
Click apply, ok and restart server

Tasks:

- [x] Integration with Google
- [ ] Create home page
- [ ] Authorization Server
- [ ] Migrate to JPA
- [ ] Admin Module
- [ ] Expose services via JAX-RS and JAX-WS


