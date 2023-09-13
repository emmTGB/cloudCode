import javax.management.openmbean.OpenDataException;

class OpenException extends Throwable{

}

class CloseException extends OpenException{

}
class what extends Exception{

}

public class OpenTXT {
    public OpenTXT() throws what{

    }
    public static int open(){
        return -1;
    }
    public void f() throws OpenException{
        throw new OpenException();
    }
    public static void read() throws OpenException, CloseException {//声明此函数会抛出此类异常，则在调用此函数时需要准备处理异常的try-catch
        if(open() == -1){
            throw new OpenException();
        }else{
            throw new CloseException();
        }
    }

    public static void main(String[] args){
        try{
            read();
        }catch (CloseException e){

        }catch(OpenException o){//当两种错误互为父子时，应将子类至于前方
            System.out.println("ERROR");
        }catch(Exception e){//任意异常，运行时异常如ArrayIndexOutOfBoundsException不需要throws声明，若此类一场没有正常捕捉则会导致程序终止

        }
    }
}

//当Override一个函数时，子类函数不能抛出比父类函数更多的异常,其抛出范围包含于父类函数的抛出范围
//在子类构造函数中必须声明父类可能抛出的全部异常

class newClass extends OpenTXT{

    public newClass() throws what, CloseException {
    }//必须声明父类构造函数的所有异常，此后可以新增异常

    @Override
    public void f() throws CloseException{
    }//可以不抛出，可以抛出OpenException以及其子类，但是不能抛出去其他的如CloseException
}