/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.cache.script.interpreter.instructions;

import net.runelite.cache.script.interpreter.Frame;
import net.runelite.cache.script.interpreter.InstructionContext;
import net.runelite.cache.script.interpreter.InstructionHandler;
import net.runelite.cache.script.interpreter.ScriptInstruction;
import net.runelite.cache.script.interpreter.Stack;
import net.runelite.cache.script.interpreter.StackContext;

public class StringAppend extends InstructionHandler
{
	@Override
	public void execute(Frame frame, InstructionContext ctx)
	{
		Stack stringStack = frame.getStringStack();
		ScriptInstruction scriptInstruction = ctx.getScriptInstruction();
		int number = scriptInstruction.getIop();

		for (int i = 0; i < number; ++i)
		{
			StackContext sctx = stringStack.pop();
			ctx.popsString(sctx);
			sctx.poppedBy(ctx);
		}

		// push appended string
		StackContext sctx = new StackContext(ctx, null);
		stringStack.push(sctx);
		ctx.pushesString(sctx);
	}

}