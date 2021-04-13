# Kubernetes components

There are lot of components in Kubernetes but the most important components that are most used would be

* Node
* Pod
* Service
* Ingress
* ConfigMap
* Secrets
* Volumes
* Deployment
* StatefulSet

## Node
In order to start with running an application, we need a server which can be a Physical server or a Virtual Machine. In Kubernetes terms it is stated as Node.

## Pod
Pod is the smallest unit in Kubernetes, and It is an abstraction over the Container. Well we already have a container now why do we need the abstraction such as Pod?
Kubernetes was designed in such a way that we can use its technology to run any container runtime. Each Pod will have a virtual static IP address assigned to it. Also, it’s worth noting that all the Pods as ephemeral, in the sense if the Pods dies all the resources corresponding to the Pod are also lost.

## Service
Since Pods are ephemeral in nature if a Pod dies and a new pod replaces the old one, the old IP address associated to the old pod will also be replaced with the new IP address. We need to separate the IP address assignment with the Life Cycle of the Pod this is where the Service component is used. Service is an abstraction which defines a logical set of Pods and a policy to access them. They help connecting applications together or users with applications. Service will get a permanent IP address called as Cluster IP, which is assigned to it by Kubernetes and it is independent of the Pod Life Cycle.

## Ingress
In order to externally access your internal applications inside your cluster you can create an external service, but the way in which we access the service is via the Node IP address and the Port with http protocol for example, http://169.124.124.1:32000 where 32000 is the Node port. But you do not want to expose your Node IP address externally. This where Ingress component is used where you would create an internal service instead of an external service and the external requests are forwarded via Ingress to the Internal service and then eventually to the appropriate pods.

## ConfigMap
ConfigMap component of Kubernetes is the external configuration of your application. If you have an application that needs a connection to a database, you would generally keep all the configuration details in the application properties file. If there is any change made to the database connection string, you have to recompile and redeploy the entire application which is a very painful and tedious process. In order to avoid this complexity, the Kubernetes ConfigMap component is used and the information like database connection strings are stored here and this component would be linked to your application pod. In case if there is any change in the configs, we can simply change the value in the ConfigMap and that’s it, you do not have to re-build or re-deploy your application.

## Secrets
It is similar to the ConfigMap component of Kubernetes, but we would store application secretes in this case. This will have values such as database username/password. All the values are stored in Base-64 encoded format. Similar to ConfigMap, the Secretes component is also attached to the application Pod.

## Volumes
If you have a Database pod running on your Kubernetes Node and if the Database Pod is restarted in any case, all the data stored inside the pod will be lost. But we need our data to be persistent. This is where we use Volumes component of Kubernetes. It basically attaches the physical storage on your hard drive to your database pod. The storage can be on the same node where the database pod is running or it can exist completely outside of the Kubernetes cluster. The important point to note here is that the Kubernetes does not manage your data persistence, you have to mange it yourself manually or by any of the automated processes that you define.

## Deployment
The Deployment component of Kubernetes specifies the blueprint of your application Pods. In other words you will not be creating pods by yourself, instead you would create a deployment for your application pod and specify the requirements. One of the advantages of distributed systems is replication. The Deployment component of Kubernetes elevates this exact advantage by using replication set.

## StatefulSet
StatefulSet component is used when your application needs to manage stateful information. For example, if you are hosting a database application in your Kubernetes cluster, using a deployment component is just not enough because all the changes/updates made to your database should be synced across all the instances. This is where Statefulset comes into play. The Stateful set not only manages the replication but also manages the data synchronization.

# Architecture of Kubernetes
![Kubernates Architechture](https://miro.medium.com/max/1050/1*iaQlYvTG7NhAjlu9sdXNyA.png)

Kubernetes also follows a Master-Slaves architecture
## Master
Master is the controlling element of the cluster. It is the only endpoint that is open to the users of the cluster. For the purpose of fault-tolerance, one cluster may have multiple masters. Master has the following 4 parts:

### 1. API server
This is the front end that communicates with the user. It is a REST-based API that is designed to consume JSON inputs. As a default, it runs in port 443.
### 2. Scheduler
Scheduler watches API server for new Pod requests. It communicates with Nodes to create new pods and to assign work to nodes while allocating resources or imposing constraints.
### 3. Cluster store
Cluster store is a persistent storage holding cluster states and configuration details. It uses ETCD (open-source distributed key-value store) to store these data.
### 4. Controller
Includes Node controller, Endpoint Controller, Namespace Controller, etc.

## Nodes (Slaves/Minions)
Nodes are the workers. They are the ones that do all the “Work” assigned to the cluster. Inside a Node, there are 3main components, apart from the “Pods” (I will talk about Pods later on). Those 3 parts are :

### 1. Kubelet
Kublets do a lot of work inside a Node. They register the nodes with the cluster, watch for work assignments from the scheduler, instantiate new Pods, report back to the master, etc.
### 2. Container Engine
Container Engine is the responsible person for managing containers. It does all the image pulling, container stopping, starting, etc. Most widely used container engine is Docker. However, you can also use Rocket for this.
### 3. Kube Proxy
Kube Proxy is responsible for assigning IP addresses per pod. Each time a pod creates, a new IP address will be allocated for that pod. Kube Proxy also does the Loadbalancing work.
Apart from those mentioned components, Nodes have their own default pods like logging, health checking, DNS, etc. Each node expose 3 read-only endpoints through (usually) localhost:10255. Those endpoints are,
`/specs`
`/healthz`
`/pods`

# Services types
There are 5 types of Services available in Kuberntes which we can choose according to our purpose: 
### 1. ClusterIP
Exposes the service on a cluster-internal IP. Choosing this value makes the service only reachable from within the cluster. This is the default `ServiceType`.
### 2. NodePort
Exposes the service on each Node’s IP at a static port (the NodePort). A ClusterIP service, to which the NodePort service will route, is automatically created. You’ll be able to contact the NodePort service, from outside the cluster, by requesting `<NodeIP>:<NodePort>`.
### 3. LoadBalancer
Exposes the service externally using a cloud provider’s load balancer. NodePort and ClusterIP services, to which the external load balancer will route, are automatically created.
### 4. ExternalName
Maps the service to the contents of the `externalName` field (e.g. foo.bar.example.com), by returning a `CNAME` record with its value. No proxying of any kind is set up. This requires version 1.7 or higher of `kube-dns`
