package de.widdix.awscftemplates.ec2;

import com.amazonaws.services.cloudformation.model.Parameter;
import de.widdix.awscftemplates.ACloudFormationTest;
import de.widdix.awscftemplates.Context;
import org.junit.Test;

public class TestAL2MutablePrivate extends ACloudFormationTest {

    @Test
    public void test() {
        final Context context = new Context();
        final String vpcStackName = "vpc-2azs-" + this.random8String();
        final String natStackName = "vpc-nat-gateway-" + this.random8String();
        final String stackName = "al2-mutable-private-" + this.random8String();
        final String classB = "10";
        try {
            this.createStack(context, vpcStackName,
                    "vpc/vpc-2azs.yaml",
                    new Parameter().withParameterKey("ClassB").withParameterValue(classB)
            );
            try {
                this.createStack(context, natStackName,
                        "vpc/vpc-nat-gateway.yaml",
                        new Parameter().withParameterKey("ParentVPCStack").withParameterValue(vpcStackName)
                );
                try {
                    this.createStack(context, stackName,
                            "ec2/al2-mutable-private.yaml",
                            new Parameter().withParameterKey("ParentVPCStack").withParameterValue(vpcStackName),
                            new Parameter().withParameterKey("BackupRetentionPeriod").withParameterValue("0")
                    );
                    // TODO how can we check if this stack works?
                } finally {
                    this.deleteStack(context, stackName);
                }
            } finally {
                this.deleteStack(context, natStackName);
            }
        } finally {
            this.deleteStack(context, vpcStackName);
        }
    }

}
