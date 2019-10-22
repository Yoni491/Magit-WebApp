package Users;

public class PR {
    public String RepoName;
    public String Sender;
    public String Receiver;
    public String SenderBranch="";
    public String ReceiverBranch="";
    public String SenderCommitSha1;
    public String ReceiverCommitSha1;
    public String purpose;
    public String path;
    //boolean isPR;
    public PR(String RepoName, String Sender, String Receiver, String SenderBranch, String ReceiverBranch, String SenderCommitSha1
    ,String ReceiverCommitSha1,String purpose,String path)
    {
        this.RepoName=RepoName;
        this.Sender=Sender;
        this.Receiver=Receiver;
        this.SenderBranch=SenderBranch;
        this.ReceiverBranch=ReceiverBranch;
        this.SenderCommitSha1=SenderCommitSha1;
        this.ReceiverCommitSha1=ReceiverCommitSha1;
        this.purpose=purpose;
        this.path=path;
    }
}
