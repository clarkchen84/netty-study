### NIO
#### NIO的做成
1. Channel
    * 通道，和IO中的stream 是差不多一个等级的，只不过Stream是单向的，channel是双向的，既可以用来进行读操作
又可以进行写操作
    * Channel 中的主要实现有： FileChannel(文件),DatagramChannel(UDP),SocketChannel(TCP client),和ServerSocketChannel(TCP server).
2. Buffers
   * 缓冲区
   * Buffer 中的实现有ByteBuffer,CharBuffer,DoubleBuffer,FloatBuffer,InBuffer,LongBuffer,ShortBuffer
3. Selectors
   * 选择器： 运行单线程处理多个Channel，如果应用打开了多个通道，但是每个连接的连接都很低，使用Selector就会很方便，
例如，一个聊天服务器中， 要使用Selector，得向Selector注册Channel，调用Select()方法，这个方法会一直阻塞到某个
注册的通道有事件就绪，一旦这个方法返回，线程就可以处理这些事件，事件的例子有如果新的连接进来，数据接收等。
4. 其他组件：Pipe和FileLock等，是上面三个组件使用的工具类
#### Channel
* Channel 是一个通道，可以通过他读取数据，就像水管一样，网络数据通过Channel读取或写入，通道与流不同之处
在于通道是是双向的， 流只是在一个方向上移动， 一个流必须是InputStream，或者OutputStream的子类， 而通道
可以用于读写或者同时用于读写， 因为Channel是全双工的，所以他可以比流更好的映射到底层操作系统的api。
  
* NIO中通过channel封装了对数据源的操作， 通过channel我们可以操作数据源， 但是又不必关心数据源的具体物理
结构，这个数据源可能是多种的， 比如可以是文件，也可以是网络Socket，在大多数应用汇总给你，channel与文件描述
符或者socket是一一对应的，channel用于在字节缓冲区和位于通道另一侧的实体之间有效地址传输数据。

* 与缓冲区不同， 通道API主要是由接口指定， 不同的操作系统上，操作系统通道实现会有别致的差异，所以通道api仅仅
描述了可以做什么，因此很自然的，通道实现经常使用操作系统的本地代码，通道接口允许以一种受控制切可以一致的方式来实现
底层的I/O服务
  
* Channel是一个对象，可以读取和写入数据，NIO和BIO比较
    1. 通道就像是流， 所有数据通过Buffer对象来处理， 永远不会将字节写入到通道中，相反将数据写入到包含一个
或者多个自己的缓冲区，同样，不会直接在通道中读取字节，而是将数据从通道中读入到缓冲区，在从缓冲区读入这个字节。
    2.  NIO是从通道中读取数据，有可以写数据到通道， 但是流是单向的
    3. 通道可以异步的读写
    4. 通道中的数据总是先读写到一个Buffer 

* FileChannel 
    * 基本API
        * ![](img/FileChannel.png)
    * FileChannelDemo
        1. 创建FileChannel
           * 打开FileChannel
           * 在使用FileChannel之前，必须先打开它，但是，我们无法直接打开一个
              FileChannel，需要难过通过InputStream,OutputStream 后 RandomAccessFile
              获取一个FileChannel实例

        2. 创建Buffer
            * 调用多个Read方法之一，从FileChannel中读取数据：
                1. 分配一饿buffer， 从FileChannel中读取数据放入到Buffer中，然后调用FileChannel.read()
方法，该方法将数据从FileChannel读取到Buffer中， read()方法返回的int值表示有多少个字节被读取到了Buffer中，
如果返回-1, 表示读到了文件的末尾。
    
        3. 读取数据到Buffer

         


