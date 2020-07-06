I got it working only on https://www.katacoda.com/courses/jenkins/build-docker-images
I got it working in 2 ways:
1) By making a Maven item ( needs maven plugin) - this works. Has to be along with a Docker Build and Publish post build
2) By using freestyle item ( by writring a script) - works


Plugins needed:
- Docker plugin under Cloud Providers header
- Maven Integration plugin
- CloudBees Docker Build and Publish plugin
- Git hub

Important things to configue:
- Configure Docker Agent Template
- Configue Could/docker under Configure System 

links:
https://plugins.jenkins.io/docker-build-publish/
https://medium.com/@gustavo.guss/jenkins-building-docker-image-and-sending-to-registry-64b84ea45ee9 (did not test this one)


Configuration steps

Within the Dashboard, select Manage Jenkins on the left.
On the Configuration page, select Manage Plugins.
Manage Plugins page will give you a tabbed interface. Click Available to view all the Jenkins plugins that can be installed.
Using the search box, search for Docker. There are multiple Docker plugins, select Docker using the checkbox under the Cloud Providers header.

-----------------------------------------------
Once again, select Manage Jenkins.
Select Configure System to access the main Jenkins settings.
At the bottom, there is a dropdown called Add a new cloud. Select Docker from the list.
The Docker Host URI is where Jenkins launches the agent container. In this case, we'll use the same daemon as running Jenkins, but you could split the two for scaling. Enter the URL tcp://172.17.0.29:2345
Use Test Connection to verify Jenkins can talk to the Docker Daemon. You should see the Docker version number returned.
-----------------------------------------------
	Task: Configure Plugin

This step configures the plugin to communicate with a Docker host/daemon.

Once again, select Manage Jenkins.
Select Configure System to access the main Jenkins settings.
At the bottom, there is a dropdown called Add a new cloud. Select Docker from the list.
The Docker Host URI is where Jenkins launches the agent container. In this case, we'll use the same daemon as running Jenkins, but you could split the two for scaling. Enter the URL tcp://172.17.0.31:2345
Use Test Connection to verify Jenkins can talk to the Docker Daemon. You should see the Docker version number returned.

The Host IP address is the IP of your build agent / Docker Host.
----------------------------------------------
Task: Configure Docker Agent Template
The Docker Agent Template is the Container which will be started to handle your build process.
Click Docker Agent templates... and then Add Docker Template. You can now configure the container options.
Set the label of the agent to docker-agent. This is used by the Jenkins builds to indicate it should be built via the Docker Agent we're defining.
For the Docker Image, use benhall/dind-jenkins-agent:v2. This image is configured with a Docker client and available at https://hub.docker.com/r/benhall/dind-jenkins-agent/
Under Container Settings, In the "Volumes" text box enter /var/run/docker.sock:/var/run/docker.sock. This allows our build container to communicate with the host.
For Connect Method select Connect with SSH. The image is based on the Jenkins SSH Slave image meaning the default Inject SSH key will handle the authenication.
Make sure it is Enabled.
Click Save.
-------------------------------------------------	
	Task: Create New Job

On the Jenkins dashboard, select Create new jobs
Give the job a friendly name such as Katacoda Jenkins Demo, select Freestyle project then click OK.
The build will depend on having access to Docker. Using the "Restrict where this project can be run" we can define the label we set of our configured Docker agent. The set "Label Expression" to docker-agent. You should have a configuration of "Label is serviced by no nodes and 1 cloud".

If you see the error message There’s no agent/cloud that matches this assignment. Did you mean ‘master’ instead of ‘docker-agent’?, then the Docker plugin and the Docker Agent has not been Enabled. Go back to configure the system options and enable both checkboxes.

Select the Repository type as Git and set the Repository to be https://github.com/katacoda/katacoda-jenkins-demo.
We can now add a new build step using the Add Build Step dropdown. Select Execute Shell.
Because the logical of how to build is specified in our Dockerfile, Jenkins only needs to call build and specify a friendly name.

	
Shell script for Freestyle project	above
----------------------------------
	ls
docker info
docker build -t katacoda/jenkins-demo:${BUILD_NUMBER} .
docker tag katacoda/jenkins-demo:${BUILD_NUMBER} katacoda/jenkins-demo:latest
docker images
docker  login -u=dockeridiotfool -p=pwd
docker build -t dockeridiotfool/spring-boot-postgres .
docker push dockeridiotfool/spring-boot-postgres