package Users;

import Objects.Commit.Commit;

public class Message {
    public String RepoName;
    public String Sender;
    public String Receiver;
    public String SenderBranch="";
    public String ReceiverBranch="";
    public String SenderCommitSha1;
    public String PRmsg;
    public String msg;
    //boolean isPR;
    public Message(String RepoName, String Sender, String Receiver, String SenderBranch, String ReceiverBranch, String SenderCommitSha1,String PRmsg)
    {//For PR
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        this.SenderBranch=SenderBranch;
        this.ReceiverBranch=ReceiverBranch;
        this.SenderCommitSha1=SenderCommitSha1;
        this.PRmsg=PRmsg;
        msg="The user:"+Sender+" sent you a pull request for your repository: "+RepoName+".<p></p> Pr message: "
        +PRmsg;//need to add all the PR details.
    }
    public Message(String RepoName, String Sender, String Receiver, String SenderBranch)
    {//For push
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        this.SenderBranch=SenderBranch;
        msg="The user:"+Sender+"has pushed the branch:"+SenderBranch +"for your repository: "+RepoName+".";
    }
    public Message(String RepoName, String Sender, String Receiver, String SenderBranch,String type)
    {//For pull/The remote user accepted pr
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        this.SenderBranch=SenderBranch;
        if(type.equals("pull"))
            msg="The user:"+Sender+"has pulled the branch:"+SenderBranch +"from your repository: "+RepoName+".";
        if(type.equals("PrAccepted"))
            msg="The user:"+Sender+"has accepted your pull request for repository:"+RepoName+".";
    }
    public Message(String RepoName, String Sender, String Receiver)
    {//For fork
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        msg="The user:"+Sender+"has forked your repository:"+RepoName+".";
    }
    //need to do other constructors
}
