package com.gtp.apisupport.validation.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.gtp.apisupport.validation.enums.ApiVP;

/**
参数验证
构想是，接口定义层验证一般参数，以正则准适应性应该广

QA:
1.api定义会不会内容量太大？可忍范围内
2.实现机制:扩展式功能(拦截器)
3.文档也得支持上
4.控制的灵活性(接口级或方法级能关闭最好)

#语法:
\   将下一字符标记为特殊字符、文本、反向引用或八进制转义符（转义）
^	匹配输入字符串开始的位置
$	匹配输入字符串结尾的位置

*	零次或多次匹配前面的字符或子表达式
+	一次或多次匹配前面的字符或子表达式
?	零次或一次匹配前面的字符或子表达式
{n}		n 是非负整数。正好匹配 n 次
{n,} 	n 是非负整数。至少匹配 n 次
{n,m}	M 和 n 是非负整数，其中 n <= m。匹配至少 n 次，至多 m 次

?	当此字符紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，匹配模式是"非贪心的"

.			匹配除"\r\n"之外的任何单个字符
(pattern)	匹配 pattern 并捕获该匹配的子表达式

(?:pattern)	匹配 pattern 但不捕获该匹配的子表达式
(?=pattern)	执行正向预测先行搜索的子表达式
(?!pattern) 执行反向预测先行搜索的子表达式

x|y		匹配 x 或 y
[xyz]	字符集。匹配包含的任一字符
[^xyz]	反向字符集。匹配未包含的任何字符
[a-z]	字符范围。匹配指定范围内的任何字符
[^a-z] 反向范围字符。匹配不在指定的范围内的任何字符

\b 	匹配一个字边界，即字与空格间的位置
\B	非字边界匹配
\cx	匹配 x 指示的控制字符
\d	数字字符匹配。等效于 [0-9]
\D	非数字字符匹配。等效于 [^0-9]
\f	换页符匹配。等效于 \x0c 和 \cL。
\n	换行符匹配。等效于 \x0a 和 \cJ。
\r	匹配一个回车符。等效于 \x0d 和 \cM。
\s	匹配任何空白字符，包括空格、制表符、换页符等
\S	匹配任何非空白字符
\t	制表符匹配
\v	垂直制表符匹配
\w	匹配任何字类字符，包括下划线
\W	与任何非单词字符匹配
 */
//转用于标注与DTO上
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface ApiValidation {
	
	/**
	 * 一般用于api接口上，在特殊情况下跳过验证
	 */
	boolean value() default true;
	
	/**
	 * 验证模式，默认正则
	 */
	ApiVP pattern() default ApiVP.REGEX;
	
	/**
	 * 当pattern是正则时，这里指定正则表达式
	 */
	String regex() default "";//正则
	
	/**
	 * 错误时的说明
	 */
	String error();//错误时的说明
}
