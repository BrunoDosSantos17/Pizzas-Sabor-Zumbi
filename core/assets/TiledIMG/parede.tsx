<?xml version="1.0" encoding="UTF-8"?>
<tileset name="parede" tilewidth="32" tileheight="32" tilecount="4" columns="2">
 <properties>
  <property name="solid" type="bool" value="true"/>
 </properties>
 <image source="Preto.png" width="64" height="64"/>
 <tile id="0">
  <properties>
   <property name="solid" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="1">
  <objectgroup draworder="index">
   <object id="1" x="0.25" y="0.25" width="31.75" height="31.75">
    <properties>
     <property name="solid" type="bool" value="true"/>
    </properties>
   </object>
  </objectgroup>
 </tile>
 <tile id="2">
  <properties>
   <property name="solid" type="bool" value="true"/>
  </properties>
  <objectgroup draworder="index">
   <properties>
    <property name="solid" type="bool" value="true"/>
   </properties>
   <object id="2" x="1" y="0" width="29" height="31">
    <properties>
     <property name="solid" type="bool" value="true"/>
    </properties>
   </object>
  </objectgroup>
 </tile>
</tileset>
