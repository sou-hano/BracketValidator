fun main()
{
    val inputStr = listOf(
        "()",
        "([]){}",
        "({)}",
        "",
        "([{}([])]){}"
    )
    for (s in inputStr)
    {
        isValid(s)
    }
}

fun isValid(s: String): Boolean
{
    // 空文字はルールに従っている
    if (s.isEmpty()) return logAndReturn(s, true)

    // 開き括弧を保管するスタック
    val stack = ArrayDeque<Char>()

    for (chr in s)
    {
        when(chr)
        {
            '(', '{', '[' -> stack.addLast(chr)
            ')', '}', ']' ->
            {
                // 閉じ括弧が来た時に最新スタックを確認
                // スタックが空なら不正
                val lastBracket = stack.removeLastOrNull() ?: return logAndReturn(s, false)

                // 最新スタックと閉じ括弧が対応していなければ不正
                if(!matches(lastBracket, chr)) return logAndReturn(s, false)
            }
        }
    }

    // 処理完了後スタックが空なら正常
    // 開き括弧が残っていれば不正
    return logAndReturn(s, stack.isEmpty())
}

// 結果を出力して、真偽値を返す
private fun logAndReturn(str: String, result: Boolean): Boolean
{
    println("%-20s => %5b".format(str, result))
    return result
}

// 括弧が対応していればtrueを返す
private fun matches(open: Char, close: Char): Boolean =
    (open == '(' && close == ')') ||
    (open == '{' && close == '}') ||
    (open == '[' && close == ']')