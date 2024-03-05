package shapes;

import java.awt.Graphics;

public abstract class Shape {//抽象类，不能实例化，一旦类中存在未实现的抽象函数，则该类必须为抽象类
	
	public abstract void draw(Graphics g);//抽象函数，没有方法的实现
	
}
