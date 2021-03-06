package br.com.syssolutions.moleculas3d.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Created by jefferson on 09/11/16.
 */

public class DrawStickAndBallModel {


    private Array<ModelInstance> instances;
    private Molecula molecula;

//    private AssetManager manager;
//    private static boolean LOADED = false;


    public DrawStickAndBallModel(Molecula molecula) {
     //   manager = new AssetManager();
        this.molecula = molecula;
        instances = new Array<ModelInstance>();

       // ModelResources.assetsLoad();

      //  assetsLoad();


    }

    public Array<ModelInstance> getModel() {


        for (int i = 0; i < molecula.atomos.size(); i++) {
            ModelInstance sphereInstace = new ModelInstance(ModelResources.getSPHERE());

            //Definir posição:
            sphereInstace.transform.setToTranslation(molecula.atomos.get(i).x,
                    molecula.atomos.get(i).y,
                    molecula.atomos.get(i).z);

            //Definir tamanho do Átomo
            float raioAtomico;
            raioAtomico = molecula.atomos.get(i).getRaioAtomico(molecula.atomos.get(i).simbolo) / 1.4f;
            sphereInstace.transform.scale(raioAtomico, raioAtomico, raioAtomico);

            this.instances.add(sphereInstace);

            float[] cores = molecula.atomos.get(i).getCor();
            float r = cores[0];
            float g = cores[1];
            float b = cores[2];
            float a = cores[3];

            this.instances.get(i).materials.get(0).set(ColorAttribute.createDiffuse(r, g, b, a));


        }


        for (int i = 0; i < molecula.ligacoes.size(); i++) {


            Ligacao lig = molecula.ligacoes.get(i);

            Atomo primeiro = lig.primeiroAtomo;
            Atomo segundo = lig.segundoAtomo;

            Vector3 posA = new Vector3(primeiro.x, primeiro.y, primeiro.z);
            Vector3 posB = new Vector3(segundo.x, segundo.y, segundo.z);


            Vector3 v3 = posA.cpy().add(posB).scl(0.5f);
            Vector3 v4 = posB.cpy().sub(v3).nor();
            Vector3 v5 = v4.cpy().nor().crs(Vector3.Y).nor();


            ModelInstance ligacaoInstance;


            switch (lig.ordemLigacao) {
                default:
                    ligacaoInstance = new ModelInstance(ModelResources.getBOND1());
                    break;
                case 2:
                    ligacaoInstance = new ModelInstance(ModelResources.getBOND2());
                    break;
                case 3:
                    ligacaoInstance = new ModelInstance(ModelResources.getBOND3());
                    break;
                case 4:
                    ligacaoInstance = new ModelInstance(ModelResources.getBOND4());
                    break;

            }


            //float distancia = v1.dst(v2);

            //ligacaoInstance.transform

            float[] cores = lig.primeiroAtomo.getCor();
            float r = cores[0];
            float g = cores[1];
            float b = cores[2];
            float a = cores[3];

            ligacaoInstance.materials.get(0).set(ColorAttribute.createDiffuse(r, g, b, a));


            //ligacaoInstanceA.transform.scl(0.9f,posA.dst(posB)/2,0.9f);
            ligacaoInstance.transform.translate(posA);


            ligacaoInstance.transform.rotate(v5,
                    -(float) Math.toDegrees(Math.acos(v4.dot(Vector3.Y))));


            this.instances.add(ligacaoInstance);


//            ModelInstance ligacaoInstance2 = new ModelInstance(bond);
//            ligacaoInstance2.transform.translate(posA);
//            ligacaoInstance2.transform.scale(0.9f,posA.dst(posB)/2,0.9f);
//
//
//
//            ModelInstance ligacaoInstance3 = new ModelInstance(bond);
//            ligacaoInstance3.transform.translate(
//                    posA.x,
//                    ligacaoInstance2.transform.getScaleY(),
//                    posA.z);
//
//
//            ligacaoInstance3.materials.get(0).set(ColorAttribute.createDiffuse(0, 0, 0, a));
//
//
//
//
//            this.instances.add(ligacaoInstance2);
//
//
//            this.instances.add(ligacaoInstance3);


        }

        return this.instances;
    }


//    private void assetsLoad() {
//        if (LOADED == false) {
//            manager.load(ESFERA_PATCH, Model.class);
//            manager.load(SINGLE_STICK_PATCH, Model.class);
//            manager.load(DOUBLE_STICK_PATCH, Model.class);
//            manager.load(TRIPLE_STICK_PATCH, Model.class);
//            manager.load(QUAD_STICK_PATCH, Model.class);
//
//            manager.finishLoading();
//
//            SPHERE = manager.get(ESFERA_PATCH, Model.class);
//            BOND1 = manager.get(SINGLE_STICK_PATCH, Model.class);
//            BOND2 = manager.get(DOUBLE_STICK_PATCH, Model.class);
//            BOND3 = manager.get(TRIPLE_STICK_PATCH, Model.class);
//            BOND4 = manager.get(QUAD_STICK_PATCH, Model.class);
//
//
//        }
//        LOADED = true;
//
//    }


}