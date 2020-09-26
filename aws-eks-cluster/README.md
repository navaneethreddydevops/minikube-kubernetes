# To upload keypair to all regions
AWS_REGIONS="$(aws ec2 describe-regions --query 'Regions[].RegionName' --output text)"
for each_region in ${AWS_REGIONS} ; do aws ec2 import-key-pair --key-name keypair --public-key-material fileb://$HOME/.ssh/id_rsa.pub --region $each_region ; done


# To Delete a Keypair:
AWS_REGIONS="$(aws ec2 describe-regions --query 'Regions[].RegionName' --output text)"
for each_region in ${AWS_REGIONS} ; do aws ec2 delete-key-pair --key-name keypair --region $each_region ; done

```
eksctl create cluster -f cluster.yaml

```