# Don't touch unless you know what you're doing!
# Use develop.yaml to manage the containers and registries for this project
#
# version=1.0.4
#
require 'yaml'
ENV['VAGRANT_DEFAULT_PROVIDER'] = 'docker'
ENV['VAGRANT_NO_PARALLEL'] = 'yes'

yaml_config = YAML.load_file('develop.yaml')
containers = yaml_config["containers"] || []
@registries = yaml_config["registries"] || []
services = yaml_config["services"] || []

DOCKER_HOST_NAME = "dockerhost"
DOCKER_HOST_VAGRANTFILE = "./dockerHost/Vagrantfile"
AUTHOR_CTR_NAME = "author"
WORKDIR_CTR_NAME = "workdir"
BUILD_CTR_NAME = "build"
BASE_CTR_NAME = "base"
GIT_CTR_NAME = "git"

#docker_login logins the registry options.
def docker_login (d, regis)
  if regis == "aws"
    @registries.each do |registry|
      if registry["name"] == "aws"
        d.username = "AWS"
        if registry["auto_generate_token"] == true
          d.password = `aws ecr get-authorization-token --output text --profile #{registry['profile']} --query authorizationData[].authorizationToken | base64 -D | cut -d: -f2`
        else
          d.password = registry["token"]
        end
        d.auth_server = registry["account_id"]+".dkr.ecr.us-east-1.amazonaws.com"
        break
      end
    end
  end
  if regis == "quay"
    @registries.each do |registry|
      if registry["name"] == "quay"
        d.username = registry["username"]
        d.password = registry["password"]
        d.auth_server = "quay.io"
        break
      end
    end
  end
end

# docker_host defines Docker host VM
def docker_host d
  d.vagrant_machine = "#{DOCKER_HOST_NAME}"
  d.vagrant_vagrantfile = "#{DOCKER_HOST_VAGRANTFILE}"
end

# common_options defines the common optios for containers and services
def common_options (d, container)
  # Deprecated section
  if container["link"] != nil
    print "develop.yaml\n"
    print "link: option is deprecated, use links: \n"
  end

  container_name = ""
  if container["image"] != nil       then d.image = container["image"] end
  if container["create_args"] != nil then d.create_args = container["create_args"] end
  if container["var_env"] != nil     then d.env = container["var_env"] end
  if container["links"] != nil
    links = container["links"]
    if !links.empty?
      links.each do |link|
        d.link(link)
      end
    end
  end
  if container["name"] != nil
    d.name = container_name = container["name"]
  end

  #default volumes for all containers
  default_volumens = ["/vagrant:/project"]
  if container_name == "#{AUTHOR_CTR_NAME}"
    default_volumens += ["/aemconfig:/bin/crx-quickstart/install/install"]
  end
  if container_name == "#{WORKDIR_CTR_NAME}" || container_name == "#{BUILD_CTR_NAME}" || container_name == "#{BASE_CTR_NAME}"
    default_volumens += ["/.m2:/root/.m2"]
  end
  if container_name == "#{BASE_CTR_NAME}"
    default_volumens += ["/aemconfig:/root/aemconfig"]
  end
  if container_name == "#{GIT_CTR_NAME}"
    default_volumens += ["/ssh/id_rsa:/root/.ssh/id_rsa","/gitconfig/.gitconfig:/root/.gitconfig"]
  end
  if container["volumes"] != nil
    d.volumes = container["volumes"] + default_volumens
  else
    d.volumes = default_volumens
  end
  if container["registry"] != nil
    docker_login d, container["registry"]
  end
end

Vagrant.configure("2") do |config|

  # Services section
  services.each do |service|
    config.vm.define service["name"] do |a|
      a.vm.provider "docker" do |d|
        #get common options for services
        common_options d, service
        # Service options
        if service["ports"] != nil then d.ports = service["ports"] end
        if service["cmd"] != nil   then d.cmd = service["cmd"].split(" ") end
        d.remains_running = true
        docker_host d
      end
    end
  end

  # Containers section
  containers.each do |container|
    config.vm.define container["name"] do |a|
      a.vm.provider "docker" do |d|
        #get common options for services
        common_options d, container
        docker_host d
      end
    end
  end
end
