# spring-boot-postgres-on-k8s
* ### Build the spring boot app
  ```shell
  ./mvnw clean package -Dmaven.test.skip=true
  ```
* ### Build Docker image
  ```shell
  docker build -t <Docker Hub account>/spring-boot-postgres-on-k8s:v1 .
  ```
* ### Push the image to Hub
  ```shell
  docker push <your Docker Hub account>/spring-boot-postgres-on-k8s:v1
  ```

### KUBERNETES
* ### Deploy postgres
  ```shell
  kubectl create -f k8s/postgres.yml
  ```
* ### Create Config Map
  ```shell
  kubectl create configmap hostname-config --from-literal=postgres_host=$(kubectl get svc postgres -o jsonpath="{.spec.clusterIP}")
  ```
* ### Replace `<Docker Hub account>` with your account name in `k8s/spring-app.yml`, then deploy the app
  ```shell
  kubectl create -f k8s/spring-app.yml
  ```
* ### Create an external load balancer
  ```shell
  kubectl expose deployment spring-boot-postgres-sample --type=LoadBalancer --port=8080
  ```