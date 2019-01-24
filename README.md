# Architecture Summary

**Record architecture design and thought during the work and study**

- **auth-server** union auth server provides unified auth interface and encapsulates thirdparty oauth services including wechat, weibo, google, etc. auth server also manages token,which generated with jwt algorithm to store user basic info

- **platformization services** 
  - **access control** unified access control rules

- **RBAC** role based access control model

- **RMS(resource management system)** unified resource management system which can be used for different kind of system like cms, course management, video management,etc.

- **abtest experiment platform**  A/B Test service

- **gateway authorization and authentification** provide unified user authorization and api authentification in gateway level so that each microservice 

- **backend-spec** backend development and release specification

- **distributed transaction** 2pc, 3pc, Message table, TCC, etc.

- **oauth2** authentification server with oauth2 protocol. 