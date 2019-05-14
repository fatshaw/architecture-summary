# DEVOPS

ci：gitlab ci runner

容器：docker

监控：cadvisor，prometheus，node_exporter，grafana

容器编排：k8s

## 开发流程

- ci/cd: gitlab ci，docker，k8s
- git管理：dev开发，master pull request merge，发布时打tag
- 环境：本地开发环境、k8s环境（开发环境，测试环境），staging环境，线上环境

## deployment
- green/blue deployment
    - green deployment: server for the client
    - blue deployment: deploy new version and test. if everything is good, then change blue deployment to green. 
- canary deployment
    - release deployment gradually. 10% first and test. if no problem, release the other 90%.

## scrum

* Feature team  
* Refinement->planning->review->retrospect  
* DOD: definition of done  
* Kanban: trello