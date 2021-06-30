import subprocess


def deploy():
    try:
        result = subprocess.Popen("eksctl create cluster -f cluster.yaml", \
                                  stdout=subprocess.PIPE, shell=True)
        (out, err) = result.communicate()
    except Exception:
        pass

    try:
        result = subprocess.Popen("eksctl create namespace navaneethreddydevops", \
                                  stdout=subprocess.PIPE, shell=True)
        (out, err) = result.communicate()
    except Exception:
        pass

    try:
        result = subprocess.Popen("kubectl get service kubernetes -o jsonpath='{.spec.clusterIP}'; echo", \
                                  stdout=subprocess.PIPE, shell=True)
        (out, err) = result.communicate()
    except Exception:
        pass

    try:
        result = subprocess.Popen("eksctl apply -f  proxy-environment-config.yaml", \
                                  stdout=subprocess.PIPE, shell=True)
        (out, err) = result.communicate()
    except Exception:
        pass

    try:
        result = subprocess.Popen("kubectl patch -n kube-system -p '{\"spec\": {\"template\": \
        {\"spec\": { \"containers\": [ { \"name\": \"aws-node\", \"envFrom\": [ { \"configMapRef\": {\"name\": \"proxy-variables\"}}]}]}}}}' daemonset aws-node", \
                                  stdout=subprocess.PIPE, shell=True)
        (out, err) = result.communicate()
    except Exception:
        pass

    try:
        result = subprocess.Popen(
            "kubectl set env daemonset/kube-proxy --namespace=kube-system --from=configmap/proxy-variables --containers='*'", \
            stdout=subprocess.PIPE, shell=True)
        (out, err) = result.communicate()
    except Exception:
        pass

    try:
        result = subprocess.Popen(
            "kubectl set env daemonset/aws-node --namespace=kube-system --from=configmap/proxy-variables --containers='*'", \
            stdout=subprocess.PIPE, shell=True)
        (out, err) = result.communicate()
    except Exception:
        pass

    try:
        result = subprocess.Popen("kubectl patch -n kube-system -p '{\"spec\": {\"template\": \
        {\"spec\": { \"containers\": [ { \"name\": \"kube-proxy\", \"envFrom\": [ { \"configMapRef\": {\"name\": \"proxy-variables\"}}]}]}}}}' daemonset kube-proxy", \
                                  stdout=subprocess.PIPE, shell=True)
        (out, err) = result.communicate()
    except Exception:
        pass

    try:
        result = subprocess.Popen("eksctl apply -f  kubernetes-objects/service.yaml", \
                                  stdout=subprocess.PIPE, shell=True)
        (out, err) = result.communicate()
    except Exception:
        pass

    try:
        result = subprocess.Popen("eksctl apply -f  kubernetes-objects/deployment.yaml", \
                                  stdout=subprocess.PIPE, shell=True)
        (out, err) = result.communicate()
    except Exception:
        pass


if __name__ == '__main__':
    deploy()
