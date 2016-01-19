/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Psi Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 * <p>
 * Psi is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 * <p>
 * File Created @ [11/01/2016, 22:00:30 (GMT)]
 * <p>
 * Modified based on personal preference by <WireSegal>.
 */
package getfluxed.minemagicka.network.messages;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.lang3.tuple.Pair;

import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public abstract class PlugNPlayMessage<REQ extends PlugNPlayMessage> implements Serializable, IMessage, IMessageHandler<REQ, IMessage> {

    private static final HashMap<Class, Pair<Reader, Writer>> handlers = new HashMap<Class, Pair<Reader, Writer>>();
    private static final HashMap<Class, Field[]> fieldCache = new HashMap<Class, Field[]>();

    static {
        map(byte.class, PlugNPlayMessage::readByte, PlugNPlayMessage::writeByte);
        map(short.class, PlugNPlayMessage::readShort, PlugNPlayMessage::writeShort);
        map(int.class, PlugNPlayMessage::readInt, PlugNPlayMessage::writeInt);
        map(long.class, PlugNPlayMessage::readLong, PlugNPlayMessage::writeLong);
        map(float.class, PlugNPlayMessage::readFloat, PlugNPlayMessage::writeFloat);
        map(double.class, PlugNPlayMessage::readDouble, PlugNPlayMessage::writeDouble);
        map(boolean.class, PlugNPlayMessage::readBoolean, PlugNPlayMessage::writeBoolean);
        map(char.class, PlugNPlayMessage::readChar, PlugNPlayMessage::writeChar);
        map(String.class, PlugNPlayMessage::readString, PlugNPlayMessage::writeString);
        map(NBTTagCompound.class, PlugNPlayMessage::readNBT, PlugNPlayMessage::writeNBT);
        map(ItemStack.class, PlugNPlayMessage::readItemStack, PlugNPlayMessage::writeItemStack);
        map(BlockPos.class, PlugNPlayMessage::readBlockPos, PlugNPlayMessage::writeBlockPos);
        /// Wire here!
        map(ElementList.class, PlugNPlayMessage::readElementList, PlugNPlayMessage::writeElementList);
        map(ElementCompound.class, PlugNPlayMessage::readElementComp, PlugNPlayMessage::writeElementComp);
        // Wire out.
    }

    private static Field[] getClassFields(Class<?> clazz) {
        if (fieldCache.containsValue(clazz))
            return fieldCache.get(clazz);
        else {
            Field[] fields = clazz.getFields();
            Arrays.sort(fields, (Field f1, Field f2) -> f1.getName().compareTo(f2.getName()));
            fieldCache.put(clazz, fields);
            return fields;
        }
    }

    private static Pair<Reader, Writer> getHandler(Class<?> clazz) {
        Pair<Reader, Writer> pair = handlers.get(clazz);
        if (pair == null)
            throw new RuntimeException("No R/W handler for  " + clazz);
        return pair;
    }

    private static boolean acceptField(Field f, Class<?> type) {
        int mods = f.getModifiers();
        return !(Modifier.isFinal(mods) || Modifier.isStatic(mods) || Modifier.isTransient(mods)) && handlers.containsKey(type);

    }

    private static <T> void map(Class<T> type, Reader<T> reader, Writer<T> writer) {
        handlers.put(type, Pair.of(reader, writer));
    }

    private static byte readByte(ByteBuf buf) {
        return buf.readByte();
    }

    private static void writeByte(byte b, ByteBuf buf) {
        buf.writeByte(b);
    }

    private static short readShort(ByteBuf buf) {
        return buf.readShort();
    }

    private static void writeShort(short s, ByteBuf buf) {
        buf.writeShort(s);
    }

    private static int readInt(ByteBuf buf) {
        return buf.readInt();
    }

    private static void writeInt(int i, ByteBuf buf) {
        buf.writeInt(i);
    }

    private static long readLong(ByteBuf buf) {
        return buf.readLong();
    }

    private static void writeLong(long l, ByteBuf buf) {
        buf.writeLong(l);
    }

    private static float readFloat(ByteBuf buf) {
        return buf.readFloat();
    }

    private static void writeFloat(float f, ByteBuf buf) {
        buf.writeFloat(f);
    }

    private static double readDouble(ByteBuf buf) {
        return buf.readDouble();
    }

    private static void writeDouble(double d, ByteBuf buf) {
        buf.writeDouble(d);
    }

    private static boolean readBoolean(ByteBuf buf) {
        return buf.readBoolean();
    }

    private static void writeBoolean(boolean b, ByteBuf buf) {
        buf.writeBoolean(b);
    }

    private static char readChar(ByteBuf buf) {
        return buf.readChar();
    }

    private static void writeChar(char c, ByteBuf buf) {
        buf.writeChar(c);
    }

    private static String readString(ByteBuf buf) {
        return ByteBufUtils.readUTF8String(buf);
    }

    private static void writeString(String s, ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, s);
    }

    private static NBTTagCompound readNBT(ByteBuf buf) {
        return ByteBufUtils.readTag(buf);
    }

    private static void writeNBT(NBTTagCompound cmp, ByteBuf buf) {
        ByteBufUtils.writeTag(buf, cmp);
    }

    private static ItemStack readItemStack(ByteBuf buf) {
        return ByteBufUtils.readItemStack(buf);
    }

    private static void writeItemStack(ItemStack stack, ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, stack);
    }

    private static BlockPos readBlockPos(ByteBuf buf) {
        return BlockPos.fromLong(buf.readLong());
    }

    private static void writeBlockPos(BlockPos pos, ByteBuf buf) {
        buf.writeLong(pos.toLong());
    }

    private static void writeElementList(ElementList list, ByteBuf buf) {
        NBTTagCompound cmp = new NBTTagCompound();
        list.writeToNBT(cmp);
        ByteBufUtils.writeTag(buf, cmp);
    }

    private static ElementList readElementList(ByteBuf buf) {
        NBTTagCompound cmp = ByteBufUtils.readTag(buf);
        return (new ElementList()).readFromNBT(cmp);
    }

    private static void writeElementComp(ElementCompound comp, ByteBuf buf) {
        NBTTagCompound cmp = new NBTTagCompound();
        comp.writeToNBT(cmp);
        ByteBufUtils.writeTag(buf, cmp);
    }

    private static ElementCompound readElementComp(ByteBuf buf) {
        NBTTagCompound cmp = ByteBufUtils.readTag(buf);
        return (new ElementCompound()).readFromNBT(cmp);
    }

    // The thing you override!
    public abstract IMessage handleMessage(MessageContext context);

    @Override
    public final IMessage onMessage(REQ message, MessageContext context) {
        return message.handleMessage(context);
    }

    /// Wire here

    @Override
    public final void fromBytes(ByteBuf buf) {
        try {
            Class<?> clazz = getClass();
            Field[] clFields = getClassFields(clazz);
            for (Field f : clFields) {
                Class<?> type = f.getType();
                if (acceptField(f, type))
                    readField(f, type, buf);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error at reading packet " + this, e);
        }
    }

    @Override
    public final void toBytes(ByteBuf buf) {
        try {
            Class<?> clazz = getClass();
            Field[] clFields = getClassFields(clazz);
            for (Field f : clFields) {
                Class<?> type = f.getType();
                if (acceptField(f, type))
                    writeField(f, type, buf);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error at writing packet " + this, e);
        }
    }

    private void writeField(Field f, Class clazz, ByteBuf buf) throws IllegalArgumentException, IllegalAccessException {
        Pair<Reader, Writer> handler = getHandler(clazz);
        handler.getRight().write(f.get(this), buf);
    }

    private void readField(Field f, Class clazz, ByteBuf buf) throws IllegalArgumentException, IllegalAccessException {
        Pair<Reader, Writer> handler = getHandler(clazz);
        f.set(this, handler.getLeft().read(buf));
    }

    /// Wire no longer here

    public interface Writer<T extends Object> {
        void write(T t, ByteBuf buf);
    }

    public interface Reader<T extends Object> {
        T read(ByteBuf buf);
    }

}
