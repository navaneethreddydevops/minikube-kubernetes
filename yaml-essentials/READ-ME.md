Block Style 

host: phl-42
datacenter :
  location: Dallas
  cab: 13
roles:
  - web
  - dns

Flow Style

Folding longlines of key-values it works is json

host: "phl-42"
datacenter : {location: Dallas,cab: 13}
roles: [ web , dns ]


Mappings:

list of key values pairs

Sequences:
Lists,Arrays,collections


Scalars:

Comments:

comments in yaml are defines with # 

Tags:

%TAG ! tag:hostdata:phl

Anchors:

Anchors are defined with &