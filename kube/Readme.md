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




