package de.widdix.awscftemplates.vpc;

import com.amazonaws.services.cloudformation.model.Parameter;
import de.widdix.awscftemplates.Context;
import org.junit.Test;

import java.util.Map;

public class TestVPC4AZsLegacy extends AVPCTest {

    @Test
    public void test() {
        final Context context = new Context();
        final String vpcStackName = "vpc-4azs-" + this.random8String();
        final String stackName = "vpc-4azs-legacy-" + this.random8String();
        try {
            this.createStack(context, vpcStackName,
                    "vpc/vpc-4azs.yaml",
                    new Parameter().withParameterKey("ClassB").withParameterValue("10")
            );
            final Map<String, String> vpcOutputs = this.getStackOutputs(vpcStackName);
            try {
                this.createStack(context, stackName,
                        "vpc/vpc-4azs-legacy.yaml",
                        new Parameter().withParameterKey("AZA").withParameterValue(vpcOutputs.get("AZA")),
                        new Parameter().withParameterKey("AZB").withParameterValue(vpcOutputs.get("AZB")),
                        new Parameter().withParameterKey("AZC").withParameterValue(vpcOutputs.get("AZC")),
                        new Parameter().withParameterKey("AZD").withParameterValue(vpcOutputs.get("AZD")),
                        new Parameter().withParameterKey("CidrBlock").withParameterValue(vpcOutputs.get("CidrBlock")),
                        new Parameter().withParameterKey("CidrBlockIPv6").withParameterValue(vpcOutputs.get("CidrBlockIPv6")),
                        new Parameter().withParameterKey("VPC").withParameterValue(vpcOutputs.get("VPC")),
                        new Parameter().withParameterKey("InternetGateway").withParameterValue(vpcOutputs.get("InternetGateway")),
                        new Parameter().withParameterKey("SubnetAPublic").withParameterValue(vpcOutputs.get("SubnetAPublic")),
                        new Parameter().withParameterKey("RouteTableAPublic").withParameterValue(vpcOutputs.get("RouteTableAPublic")),
                        new Parameter().withParameterKey("SubnetBPublic").withParameterValue(vpcOutputs.get("SubnetBPublic")),
                        new Parameter().withParameterKey("RouteTableBPublic").withParameterValue(vpcOutputs.get("RouteTableBPublic")),
                        new Parameter().withParameterKey("SubnetCPublic").withParameterValue(vpcOutputs.get("SubnetCPublic")),
                        new Parameter().withParameterKey("RouteTableCPublic").withParameterValue(vpcOutputs.get("RouteTableCPublic")),
                        new Parameter().withParameterKey("SubnetDPublic").withParameterValue(vpcOutputs.get("SubnetDPublic")),
                        new Parameter().withParameterKey("RouteTableDPublic").withParameterValue(vpcOutputs.get("RouteTableDPublic")),
                        new Parameter().withParameterKey("SubnetAPrivate").withParameterValue(vpcOutputs.get("SubnetAPrivate")),
                        new Parameter().withParameterKey("RouteTableAPrivate").withParameterValue(vpcOutputs.get("RouteTableAPrivate")),
                        new Parameter().withParameterKey("SubnetBPrivate").withParameterValue(vpcOutputs.get("SubnetBPrivate")),
                        new Parameter().withParameterKey("RouteTableBPrivate").withParameterValue(vpcOutputs.get("RouteTableBPrivate")),
                        new Parameter().withParameterKey("SubnetCPrivate").withParameterValue(vpcOutputs.get("SubnetCPrivate")),
                        new Parameter().withParameterKey("RouteTableCPrivate").withParameterValue(vpcOutputs.get("RouteTableCPrivate")),
                        new Parameter().withParameterKey("SubnetDPrivate").withParameterValue(vpcOutputs.get("SubnetDPrivate")),
                        new Parameter().withParameterKey("RouteTableDPrivate").withParameterValue(vpcOutputs.get("RouteTableDPrivate"))
                );
                this.testVPCSubnetInternetAccess(context, stackName, "SubnetAPublic");
                this.testVPCSubnetInternetAccess(context, stackName, "SubnetBPublic");
                this.testVPCSubnetInternetAccess(context, stackName, "SubnetCPublic");
                this.testVPCSubnetInternetAccess(context, stackName, "SubnetDPublic");
            } finally {
                this.deleteStack(context, stackName);
            }
        } finally {
            this.deleteStack(context, vpcStackName);
        }
    }

}
