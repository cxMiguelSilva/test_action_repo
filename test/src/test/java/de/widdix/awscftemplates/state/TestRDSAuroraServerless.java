package de.widdix.awscftemplates.state;

import com.amazonaws.services.cloudformation.model.Parameter;
import de.widdix.awscftemplates.ACloudFormationTest;
import de.widdix.awscftemplates.Context;
import org.junit.Test;

public class TestRDSAuroraServerless extends ACloudFormationTest {

    @Test
    public void testMySQL56() {
        final Context context = new Context();
        final String vpcStackName = "vpc-2azs-" + this.random8String();
        final String clientStackName = "client-" + this.random8String();
        final String kmsKeyStackName = "key-" + this.random8String();
        final String stackName = "rds-aurora-serverless-" + this.random8String();
        final String password = this.random8String();
        try {
            this.createStack(context, vpcStackName, "vpc/vpc-2azs.yaml");
            try {
                this.createStack(context, clientStackName,
                        "state/client-sg.yaml",
                        new Parameter().withParameterKey("ParentVPCStack").withParameterValue(vpcStackName)
                );
                try {
                    this.createStack(context, kmsKeyStackName,"security/kms-key.yaml");
                    try {
                        this.createStack(context, stackName,
                                "state/rds-aurora-serverless.yaml",
                                new Parameter().withParameterKey("ParentVPCStack").withParameterValue(vpcStackName),
                                new Parameter().withParameterKey("ParentClientStack").withParameterValue(clientStackName),
                                new Parameter().withParameterKey("ParentKmsKeyStack").withParameterValue(kmsKeyStackName),
                                new Parameter().withParameterKey("DBName").withParameterValue("db1"),
                                new Parameter().withParameterKey("DBMasterUserPassword").withParameterValue(password)
                        );
                        // TODO how can we check if this stack works? start a bastion host and try to connect?
                    } finally {
                        this.deleteStack(context, stackName);
                    }
                } finally {
                    this.deleteStack(context, kmsKeyStackName);
                }
            } finally {
                this.deleteStack(context, clientStackName);
            }
        } finally {
            this.deleteStack(context, vpcStackName);
        }
    }

    @Test
    public void testMySQL57() {
        final Context context = new Context();
        final String vpcStackName = "vpc-2azs-" + this.random8String();
        final String clientStackName = "client-" + this.random8String();
        final String kmsKeyStackName = "key-" + this.random8String();
        final String stackName = "rds-aurora-serverless-" + this.random8String();
        final String password = this.random8String();
        try {
            this.createStack(context, vpcStackName, "vpc/vpc-2azs.yaml");
            try {
                this.createStack(context, clientStackName,
                        "state/client-sg.yaml",
                        new Parameter().withParameterKey("ParentVPCStack").withParameterValue(vpcStackName)
                );
                try {
                    this.createStack(context, kmsKeyStackName,"security/kms-key.yaml");
                    try {
                        this.createStack(context, stackName,
                                "state/rds-aurora-serverless.yaml",
                                new Parameter().withParameterKey("ParentVPCStack").withParameterValue(vpcStackName),
                                new Parameter().withParameterKey("ParentClientStack").withParameterValue(clientStackName),
                                new Parameter().withParameterKey("ParentKmsKeyStack").withParameterValue(kmsKeyStackName),
                                new Parameter().withParameterKey("DBName").withParameterValue("db1"),
                                new Parameter().withParameterKey("DBMasterUserPassword").withParameterValue(password),
                                new Parameter().withParameterKey("EngineVersion").withParameterValue("5.7.mysql-aurora.2.07.1")
                        );
                        // TODO how can we check if this stack works? start a bastion host and try to connect?
                    } finally {
                        this.deleteStack(context, stackName);
                    }
                } finally {
                    this.deleteStack(context, kmsKeyStackName);
                }
            } finally {
                this.deleteStack(context, clientStackName);
            }
        } finally {
            this.deleteStack(context, vpcStackName);
        }
    }

}
