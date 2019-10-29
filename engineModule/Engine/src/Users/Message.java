package Users;

import Objects.Commit.Commit;
import Objects.Date.DateAndTime;

import java.util.Date;

public class Message {
    public String RepoName;
    public String Sender;
    public String Receiver;
    public String SenderBranch="";
    public String ReceiverBranch="";
    public String SenderCommitSha1;
    public String PRmsg;
    public String msg;
    public DateAndTime date;
    public Message(String RepoName, String Sender, String Receiver, String SenderBranch, String ReceiverBranch, String SenderCommitSha1,String PRmsg)
    {//For PR
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        this.SenderBranch=SenderBranch;
        this.ReceiverBranch=ReceiverBranch;
        this.SenderCommitSha1=SenderCommitSha1;
        this.PRmsg=PRmsg;
        date=new DateAndTime();
        msg="The user:"+Sender+" sent you a pull request for your repository: "+RepoName+".<p></p>Pr message: "
        +PRmsg+"<p></p>"+date.getDate();
    }
    public Message(String RepoName, String Sender, String Receiver, String SenderBranch)
    {//For branch delete
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        this.SenderBranch=SenderBranch;
        date=new DateAndTime();
        msg="The user:"+Sender+" has deleted the branch:"+SenderBranch +" in your repository: "+RepoName+"."+"<p></p>"+date.getDate();
    }
    public Message(String RepoName, String Sender, String Receiver, String SenderBranch,String type) {//For pull/The remote user accepted pr/Denied PR
        this.RepoName = RepoName;
        this.Sender = Sender;
        this.Receiver = Receiver;
        this.SenderBranch = SenderBranch;
        date = new DateAndTime();
        if (type.equals("pull"))
            msg = "The user:" + Sender + " has pulled the branch:" + SenderBranch + " from your repository: " + RepoName + "." + "<p></p>" + date.getDate();
        if (type.equals("PrAccepted"))
            msg = "The user:" + Sender + " has accepted your pull request for repository:" + RepoName + "." + "<p></p>" + date.getDate();
    }
    public Message(String RepoName, String Sender, String Receiver, String SenderBranch,String type,String denyReason) {//Denied PR
        this.RepoName = RepoName;
        this.Sender = Sender;
        this.Receiver = Receiver;
        this.SenderBranch = SenderBranch;
        date = new DateAndTime();
        if (type.equals("PrDenied")) {
            msg = "The user:" + Sender + " has denied your pull request for the repository:" + RepoName + "." + "<p></p>";
        if(!denyReason.equals(""))
        {
            msg=msg+"Deny reason: "+denyReason+ "<p></p>";
        }
        msg=msg+date.getDate();
        }
    }
    public Message(String RepoName, String Sender, String Receiver)
    {//For fork
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        date=new DateAndTime();
        msg="The user:"+Sender+" has forked your repository:"+RepoName+"."+"<p></p>"+date.getDate();
    }
    //need to do other constructors
}
