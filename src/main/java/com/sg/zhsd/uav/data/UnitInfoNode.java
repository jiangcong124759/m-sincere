package com.sg.zhsd.uav.data;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="单位节点")
@JsonIgnoreProperties(value = {"handler"})
public class UnitInfoNode implements Serializable {
    @ApiModelProperty(value="单位Id")
    private String id;
    @ApiModelProperty(value="单位名称")
    private String name;
    @ApiModelProperty(value="单位级别（1：国网公司，2：省级，3：市区县级，4工区，5：班组）")
    private int level;
    @ApiModelProperty(value="子单位")
    private List<UnitInfoNode> childNode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<UnitInfoNode> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<UnitInfoNode> childNode) {
        this.childNode = childNode;
    }
    
}