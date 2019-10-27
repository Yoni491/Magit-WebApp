package Users;

import Objects.Commit.Commit;

public class Message {
    public String RepoName;
    public String Sender;
    public String Receiver;
    public String SenderBranch="";
    public String ReceiverBranch="";
    public String SenderCommitSha1;
    public String msg;
    //boolean isPR;
    public Message(String RepoName, String Sender, String Receiver, String SenderBranch, String ReceiverBranch, String SenderCommitSha1)
    {//For PR
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        this.SenderBranch=SenderBranch;
        this.ReceiverBranch=ReceiverBranch;
        this.SenderCommitSha1=SenderCommitSha1;
        msg="The user:"+Sender+" sent you a pull request for your repository: "+RepoName+".";
    }
    public Message(String RepoName, String Sender, String Receiver, String SenderBranch)
    {//For push
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        this.SenderBranch=SenderBranch;
        msg="The user:"+Sender+"has pushed the branch:"+SenderBranch +"for your repository: "+RepoName+".";
    }
    public Message(String RepoName, String Sender, String Receiver, String SenderBranch,String pull)
    {//For pull
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        this.SenderBranch=SenderBranch;
        msg="The user:"+Sender+"has pulled the branch:"+SenderBranch +"from your repository: "+RepoName+".";
    }
    public Message(String RepoName, String Sender, String Receiver)
    {//For fork
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        this.SenderBranch=SenderBranch;
        msg="The user:"+Sender+"has forked your repository:"+RepoName+".";
    }


    //need to do other constructors
}
