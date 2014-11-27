/**
 *   Copyright 2014 LiJohnson. (https://github.com/LiJohnson)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import java.lang.reflect.Field;

/**
 * 打印一个实体类,利用反射机制,获取带有get方法的属性的值,
 * 再通过重写toString方法输出各值
 * @author LiJohnson (https://github.com/LiJohnson)
 * @version 1.0.0
 * @link https://github.com/LiJohnson/java2string
 */
public class BaseEntity {

	/**
	 * 获取一个属性的值
	 * @param fieldName
	 * @return
	 */
	private Object fieldValue( String fieldName ){
		try {
			return this.getClass().getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), (Class<?>[]) null).invoke(this, (Object[]) null);
		} catch (Exception e) {
			return "error" ;
		}
	}

	/**
	 * 打印一个实例
	 * 格式: fieldName		value		type
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append("\n<pre>-------------------start---------------------- \n");
		buf.append("class Name \t\t" +this.getClass().getName() +"\n");
		Field[] fields = this.getClass().getDeclaredFields();
		for( Field field : fields ){
			String name = field.getName();
			Object object = this.fieldValue(name);
			buf.append(name + "\t\t" + (object == null ? "null" : object.toString()) + "\t\t" +field.getType().getName() + "\n");
		}
		buf.append("-------------------end  ----------------------</pre>\n");
		return buf.toString();
	}
}