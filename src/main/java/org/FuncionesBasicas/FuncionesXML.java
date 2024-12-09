package org.FuncionesBasicas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class FuncionesXML {
    private String path;
    private List<Producto> productos;

    public FuncionesXML(String path) {
        this.path = path;
        productos = new ArrayList<>();
    }

    private void crearArchivo(Document documento){
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(this.path));




            transformer.transform(source, result);

        }catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public Document obtenerInventario(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =factory.newDocumentBuilder();
            Document documento = builder.parse(new File(this.path));
            documento.getDocumentElement().normalize();


            return documento;

        }catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarInventario(){
        Document documentoInventario = obtenerInventario();
        Element raiz = documentoInventario.getDocumentElement();
        NodeList listaProductos = raiz.getElementsByTagName("producto");

        for(int i = 0; i < listaProductos.getLength(); i++){
            Node nodoProdcto = listaProductos.item(i);

            if(nodoProdcto.getNodeType() == Node.ELEMENT_NODE){
                Element productoEncontrado = (Element) nodoProdcto;
                String nombre = productoEncontrado.getElementsByTagName("nombre").item(0).getTextContent();
                String categoria = productoEncontrado.getElementsByTagName("categoria").item(0).getTextContent();
                String precio = productoEncontrado.getElementsByTagName("precio").item(0).getTextContent();
                String stock = productoEncontrado.getElementsByTagName("stock").item(0).getTextContent();
                System.out.println("\nPRODUCTO: "+(i+1)+"\nNombre: "+nombre+"\nCategoria: "+categoria+"\nPrecio: "+precio+"\nStock: "+stock);
            }

        }
        return;
    }

    public void inicializarArchivo(Producto nuevoProducto){
        try {
            File xmlFile = new File(this.path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento;

            if(xmlFile.exists()  && xmlFile.isFile() ){
                documento = obtenerInventario();
            }else{
                documento = builder.newDocument();
                Element raiz = documento.createElement("Productos");
                documento.appendChild(raiz);
            }

            Element raiz = (Element) documento.getDocumentElement();
            Element producto = documento.createElement("producto");
            raiz.appendChild(producto);

            Element nombre =  documento.createElement("nombre");
            producto.appendChild(nombre);

            Element categoria = documento.createElement("categoria");
            categoria.appendChild(documento.createTextNode(nuevoProducto.getCategoria()));
            producto.appendChild(categoria);

            nombre.appendChild(documento.createTextNode(nuevoProducto.getNombre()));
            Element precio = documento.createElement("precio");
            precio.appendChild(documento.createTextNode(String.valueOf(nuevoProducto.getPrecio())));
            producto.appendChild(precio);

            Element stock = documento.createElement("stock");
            stock.appendChild(documento.createTextNode(String.valueOf(nuevoProducto.getStock())));
            producto.appendChild(stock);

            crearArchivo(documento);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void ingresarProducto(Producto nuevoProducto){
        try{
            productos.add(nuevoProducto);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =factory.newDocumentBuilder();
            Document documento = builder.newDocument();



            Element raiz = documento.createElement("Productos");
            documento.appendChild(raiz);

            for(Producto producto : productos){
                Element etiquetaProducto = documento.createElement("producto");
                raiz.appendChild(etiquetaProducto);

                Element nombre = documento.createElement("nombre");
                nombre.appendChild(documento.createTextNode(producto.getNombre()));
                etiquetaProducto.appendChild(nombre);

                Element categoria = documento.createElement("categoria");
                categoria.appendChild(documento.createTextNode(producto.getCategoria()));
                etiquetaProducto.appendChild(categoria);

                Element precio = documento.createElement("precio");
                precio.appendChild(documento.createTextNode(String.valueOf(producto.getPrecio())));
                etiquetaProducto.appendChild(precio);

                Element stock = documento.createElement("stock");
                stock.appendChild(documento.createTextNode(String.valueOf(producto.getStock())));
                etiquetaProducto.appendChild(stock);
            }

            crearArchivo(documento);
            System.out.println("Producto agregado con exito");

        }catch (ParserConfigurationException e){
            System.out.println("Error: "+e.getMessage());
        }

    }

}
