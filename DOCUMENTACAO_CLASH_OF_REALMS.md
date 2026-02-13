# 📚 Documentação Completa - Clash of Realms

<div align="center">

![Version](https://img.shields.io/badge/version-1.0-blue)
![Java](https://img.shields.io/badge/Java-20-orange)
![License](https://img.shields.io/badge/license-Educational-green)

**Um RPG mágico de luta em turnos desenvolvido em Java**

</div>

---

## 📋 Índice

1. [Visão Geral](#-visão-geral)
2. [Arquitetura do Sistema](#-arquitetura-do-sistema)
3. [Diagramas UML](#-diagramas-uml)
4. [Documentação das Classes](#-documentação-das-classes)
5. [Fluxogramas](#-fluxogramas)
6. [Mecânicas do Jogo](#-mecânicas-do-jogo)
7. [Guia de Uso](#-guia-de-uso)
8. [Exemplos de Código](#-exemplos-de-código)
9. [Estrutura de Diretórios](#-estrutura-de-diretórios)

---

## 🎯 Visão Geral

### Descrição do Projeto

**Clash of Realms** é uma aplicação Java desenvolvida com foco em conceitos de **Programação Orientada a Objetos (POO)**, implementando:

- ✅ Classes concretas e abstratas
- ✅ Métodos concretos e abstratos
- ✅ Herança e polimorfismo
- ✅ Interfaces
- ✅ Composição e agregação
- ✅ Encapsulamento

### Objetivo Educacional

O projeto demonstra a aplicação prática de conceitos fundamentais de POO através de um jogo de RPG simples, sem interface gráfica, focando na lógica de programação e arquitetura de software.

### Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| Java | 20 | Linguagem principal |
| NetBeans | 18 | IDE de desenvolvimento |
| JUnit | - | Testes unitários |

---

## 🏗️ Arquitetura do Sistema

### Padrões de Design Implementados

1. **Template Method Pattern**
   - Classe abstrata `Heroi` define o template
   - Subclasses implementam comportamentos específicos

2. **Strategy Pattern**
   - Cada tipo de herói tem estratégias diferentes de ataque/cura

3. **Composite Pattern**
   - `ArrayList<Heroi>` gerencia coleção de heróis

### Estrutura de Pacotes

```
ClashOfRealms/
│
├── src/
│   ├── entity/
│   │   ├── Heroi.java (Abstrata)
│   │   ├── Guerreiro.java
│   │   ├── Arqueiro.java
│   │   └── Mago.java
│   │
│   └── control/
│       └── App.java (Main)
│
├── nbproject/
│   ├── build-impl.xml
│   ├── project.properties
│   └── project.xml
│
├── build.xml
├── manifest.mf
└── README.md
```

---

## 📊 Diagramas UML

### Diagrama de Classes

```
┌─────────────────────────────────────────┐
│          <<abstract>>                   │
│             Heroi                       │
├─────────────────────────────────────────┤
│ - vida: int                             │
│ - nome: String (final)                  │
│ - idade: int                            │
│ - altura: double (final)                │
│ - nivel: int                            │
│ - morto: boolean                        │
│ - revivido: boolean                     │
│ - historico: ArrayList<Heroi>           │
├─────────────────────────────────────────┤
│ + Heroi(nome, idade, altura)            │
│ + getNome(): String                     │
│ + getVida(): int                        │
│ + getNivel(): int                       │
│ + upar(): void                          │
│ + morre(): void                         │
│ + revive(): void                        │
│ + recebeDano(val): void                 │
│ + recebeCura(val): void                 │
│ + aniversario(): void                   │
│ + compareTo(Object): int                │
│ + toString(): String                    │
│ # habilidade(Heroi): void (abstract)    │
│ # evoluir(): void (abstract)            │
└─────────────────────────────────────────┘
           ▲           ▲           ▲
           │           │           │
    ┌──────┘           │           └──────┐
    │                  │                  │
┌───┴────────┐  ┌──────┴──────┐  ┌───────┴────┐
│ Guerreiro  │  │  Arqueiro   │  │    Mago    │
├────────────┤  ├─────────────┤  ├────────────┤
│ - dano: int│  │ - dano: int │  │ - cura:int │
│            │  │ -flechas:int│  │            │
├────────────┤  ├─────────────┤  ├────────────┤
│+ evoluir() │  │+ evoluir()  │  │+ evoluir() │
│+habilidade│  │+habilidade()│  │+habilidade()│
└────────────┘  └─────────────┘  └────────────┘
```

### Diagrama de Sequência - Uso de Habilidade

```
Usuário        App         Arena        Heroi1       Heroi2
   │            │            │             │            │
   │ Escolhe 8  │            │             │            │
   ├───────────>│            │             │            │
   │            │ ID do      │             │            │
   │            │ atacante   │             │            │
   │<───────────┤            │             │            │
   │            │            │             │            │
   │ Informa P1 │            │             │            │
   ├───────────>│            │             │            │
   │            │ ID do alvo │             │            │
   │<───────────┤            │             │            │
   │            │            │             │            │
   │ Informa P2 │            │             │            │
   ├───────────>│            │             │            │
   │            │get(p1).habilidade(get(p2))           │
   │            ├───────────>│             │            │
   │            │            │habilidade() │            │
   │            │            ├────────────>│            │
   │            │            │             │recebeDano()│
   │            │            │             ├───────────>│
   │            │            │             │            │
   │            │            │      <evoluir se matar>  │
   │            │            │             │            │
```

### Diagrama de Estados do Herói

```
                    ┌─────────────┐
                    │   CRIADO    │
                    │  (Vivo)     │
                    └──────┬──────┘
                           │
                           ▼
                    ┌─────────────┐
         ┌─────────>│    VIVO     │<──────────┐
         │          │ Vida > 0    │           │
         │          └──────┬──────┘           │
         │                 │                  │
         │   recebeDano()  │                  │
         │   (Vida = 0)    │                  │
         │                 ▼                  │
         │          ┌─────────────┐           │
         │          │ ATORDOADO   │           │
         │          │(Morto=true) │           │
         │          │(Revivido=   │           │
         │          │  false)     │           │
         │          └──────┬──────┘           │
         │                 │                  │
         │   Mago.revive() │                  │
         │                 ▼                  │
         │          ┌─────────────┐           │
         │          │  REVIVIDO   │───────────┘
         │          │(Morto=false)│ recebeCura()
         │          │(Revivido=   │
         │          │  true)      │
         │          └──────┬──────┘
         │                 │
         │   recebeDano()  │
         │   (Vida = 0)    │
         │                 ▼
         │          ┌─────────────┐
         └──────────┤    MORTO    │
                    │(Morto=true) │
                    │(Revivido=   │
                    │  true)      │
                    └─────────────┘
         (Não pode mais ser revivido)
```

---

## 📖 Documentação das Classes

### 1. Classe Abstrata: `Heroi`

**Localização:** `src/entity/Heroi.java`

**Responsabilidade:** Classe base para todos os tipos de heróis, implementa comportamentos comuns e define contratos para subclasses.

#### Atributos

| Modificador | Tipo | Nome | Descrição |
|-------------|------|------|-----------|
| `private` | `int` | `vida` | Pontos de vida (0-100%) |
| `private final` | `String` | `nome` | Nome do herói (imutável) |
| `private` | `int` | `idade` | Idade em anos (0-122) |
| `private final` | `double` | `altura` | Altura em metros (imutável, max 2.74m) |
| `private` | `int` | `nivel` | Nível do herói (inicia em 1) |
| `private` | `boolean` | `morto` | Indica se está morto |
| `private` | `boolean` | `revivido` | Indica se já foi revivido |
| `private final` | `ArrayList<Heroi>` | `historico` | Lista de heróis mortos/salvos |

#### Construtor

```java
public Heroi(String nome, int idade, double altura)
```

**Validações:**
- ✅ Nome não pode ser nulo ou vazio
- ✅ Idade deve estar entre 0 e 122 anos
- ✅ Altura deve estar entre 0 e 2.74 metros

**Exceções Lançadas:**
- `NullPointerException` - Se nome for nulo
- `IllegalArgumentException` - Se validações falharem

#### Métodos Públicos

##### Getters

```java
public String getNome()      // Retorna o nome
public double getAltura()    // Retorna a altura
public int getIdade()        // Retorna a idade
public int getVida()         // Retorna pontos de vida
public int getNivel()        // Retorna o nível
public boolean getMorto()    // Retorna status de morte
public boolean getRevivido() // Retorna status de revivido
public ArrayList<Heroi> getHistorico() // Retorna histórico
```

##### Métodos de Estado

```java
public void upar()           // Incrementa o nível
public void aniversario()    // Incrementa a idade
public void morre()          // Define morto = true
public void revive()         // Define morto = false, revivido = true
```

##### Métodos de Combate

```java
public void recebeDano(int val)
```
**Descrição:** Aplica dano ao herói, reduzindo vida sem ultrapassar 0.

**Algoritmo:**
```
Para i de val até 0:
    Se vida - i >= 0:
        vida -= i
        sair do loop
```

```java
public void recebeCura(int val)
```
**Descrição:** Cura o herói, aumentando vida sem ultrapassar 100.

**Algoritmo:**
```
Para i de val até 0:
    Se vida + i <= 100:
        vida += i
        sair do loop
```

##### Método Comparable

```java
@Override
public int compareTo(Object par)
```

**Critérios de Ordenação (prioridade):**
1. Idade (crescente)
2. Altura (crescente)
3. Vida (crescente)

**Retorna:**
- `-1` se este < outro
- `1` se este > outro
- `0` se iguais

##### Método toString

```java
@Override
public String toString()
```

**Formato de Saída:**
```
[Nome] ([Status]) | Nível: [N] | [Idade] Anos e [Altura]m
```

**Status:**
- `VIVO` - morto=false
- `ATORDOADO` - morto=true, revivido=false
- `MORTO` - morto=true, revivido=true

#### Métodos Abstratos

```java
abstract public void habilidade(Heroi par)
```
**Descrição:** Define o comportamento único de cada classe de herói.

```java
abstract public void evoluir()
```
**Descrição:** Define como cada herói evolui ao subir de nível.

---

### 2. Classe Concreta: `Guerreiro`

**Localização:** `src/entity/Guerreiro.java`

**Especialidade:** Combate corpo-a-corpo com dano físico progressivo.

#### Atributos Específicos

| Modificador | Tipo | Nome | Valor Inicial | Máximo |
|-------------|------|------|---------------|---------|
| `private` | `int` | `dano` | 30 | 70 |

#### Construtor

```java
public Guerreiro(String nome, int idade, double altura)
```
Chama `super(nome, idade, altura)` e inicializa `dano = 30`.

#### Implementação: `evoluir()`

**Algoritmo:**
```java
this.upar();  // Incrementa nível
for (int i = 5; i != 0; i--) {
    if (this.dano + i <= 70) {
        this.dano += i;
        break;
    }
}
```

**Mecânica:**
- Tenta adicionar 5 pontos de dano
- Se ultrapassar 70, tenta 4, depois 3, 2, 1
- Para ao encontrar valor válido

**Progressão de Dano:**
| Evolução | Dano Total |
|----------|------------|
| Inicial | 30 |
| 1ª | 35 |
| 2ª | 40 |
| ... | ... |
| 8ª | 70 (máximo) |

#### Implementação: `habilidade(Heroi par)`

**Comportamento:** Ataque direto com dano fixo.

**Condições de Cancelamento:**
1. Atacante está morto → Dano = 0
2. Alvo é ele mesmo → Dano = 0
3. Alvo já está morto → Dano = 0

**Fluxo de Ataque:**

```
SE alvo.vida - dano <= 0:
    ├─ Aplicar dano igual à vida restante
    ├─ Marcar alvo como morto
    ├─ Evoluir atacante
    └─ Adicionar alvo ao histórico
SENÃO:
    ├─ Aplicar dano completo
    └─ SE dano < 70: incrementar dano em 1
```

**Ganho Progressivo:**
- Cada ataque bem-sucedido aumenta dano em +1
- Matar inimigo: evolui (ganha até +5 dano)

#### Método toString

```java
@Override
public String toString()
```

**Saída:**
```
[super.toString()] | Guerreiro ( Vida: [X]% ; Dano: [Y] )
```

**Exemplo:**
```
João (VIVO) | Nível: 5 | 25 Anos e 1.80m | Guerreiro ( Vida: 85% ; Dano: 45 )
```

---

### 3. Classe Concreta: `Arqueiro`

**Localização:** `src/entity/Arqueiro.java`

**Especialidade:** Ataques de longo alcance com múltiplas flechas e dano variável.

#### Atributos Específicos

| Modificador | Tipo | Nome | Inicial | Máximo |
|-------------|------|------|---------|---------|
| `private` | `int` | `dano` | 40 | 50 |
| `private` | `int` | `flechas` | 1 | ∞ |

#### Construtor

```java
public Arqueiro(String nome, int idade, double altura)
```
Inicializa: `dano = 40`, `flechas = 1`

#### Implementação: `evoluir()`

**Algoritmo:**
```java
this.upar();
if (this.getNivel() == 3) this.flechas++;
if (this.getNivel() == 5) this.flechas++;
if (this.getNivel() % 2 == 1) {
    if (this.dano + 1 <= 50) this.dano++;
}
```

**Tabela de Progressão:**

| Nível | Flechas | Dano | Evolução |
|-------|---------|------|----------|
| 1 | 1 | 40 | - |
| 2 | 1 | 40 | - |
| 3 | **2** | 41 | +1 flecha, +1 dano |
| 4 | 2 | 41 | - |
| 5 | **3** | 42 | +1 flecha, +1 dano |
| 7 | 3 | 43 | +1 dano |
| 9 | 3 | 44 | +1 dano |
| 11 | 3 | 45 | +1 dano |
| ... | ... | ... | ... |
| 21+ | 3 | 50 | (máximo) |

#### Implementação: `habilidade(Heroi par)`

**Comportamento:** Ataque múltiplo com dano aleatório por flecha.

**Condições de Cancelamento:** (iguais ao Guerreiro)

**Fluxo de Ataque Multi-Flecha:**

```
PARA cada flecha de 1 até this.flechas:
    ├─ Calcular danoReal = random(0, dano)
    │
    ├─ SE alvo.vida - danoReal > 0:
    │  └─ Aplicar danoReal e continuar próxima flecha
    │
    └─ SENÃO:
       ├─ Aplicar vida restante como dano
       ├─ Marcar alvo como morto
       ├─ Evoluir atacante
       ├─ Adicionar alvo ao histórico
       └─ PARAR (não dispara flechas restantes)
```

**Características Únicas:**
- ⚔️ Dano aleatório: `0` a `dano` (ex: 0-40)
- 🎯 Múltiplos ataques por turno
- 🛑 Para ao matar o alvo

**Exemplo de Ataque com 3 Flechas:**
```
Flecha 1: 23 de dano → Alvo: 77% vida
Flecha 2: 35 de dano → Alvo: 42% vida
Flecha 3: 18 de dano → Alvo: 24% vida
```

#### Método toString

```java
@Override
public String toString()
```

**Saída:**
```
[super] | Arqueiro ( Vida: [X]% ; Dano Máximo: [Y] ; Aljava: [Z] flecha(s) )
```

---

### 4. Classe Concreta: `Mago`

**Localização:** `src/entity/Mago.java`

**Especialidade:** Suporte - cura aliados e ressurreição.

#### Atributos Específicos

| Modificador | Tipo | Nome | Inicial | Máximo |
|-------------|------|------|---------|---------|
| `private` | `int` | `cura` | 20 | 50 |

#### Construtor

```java
public Mago(String nome, int idade, double altura)
```
Inicializa: `cura = 20`

#### Implementação: `evoluir()`

**Algoritmo:**
```java
this.upar();
if (this.cura + 5 <= 50) {
    this.cura += 5;
}
```

**Progressão de Cura:**

| Evolução | Cura | Incremento |
|----------|------|------------|
| Inicial | 20 | - |
| 1ª | 25 | +5 |
| 2ª | 30 | +5 |
| 3ª | 35 | +5 |
| 4ª | 40 | +5 |
| 5ª | 45 | +5 |
| 6ª+ | 50 | - (máximo) |

#### Implementação: `habilidade(Heroi par)`

**Comportamento:** Cura ou ressurreição de heróis.

**Tabela de Decisão:**

| Mago Morto? | Alvo Morto? | Já Revivido? | Ação |
|-------------|-------------|--------------|------|
| ✅ Sim | - | - | Nenhuma (cura = 0) |
| ❌ Não | ✅ Sim | ❌ Não | **Revive + Cura + Evolui** |
| ❌ Não | ✅ Sim | ✅ Sim | Nenhuma (cura = 0) |
| ❌ Não | ❌ Não | - | **Apenas Cura** |

**Fluxo Detalhado:**

```
SE mago.morto == true:
    └─ Aplicar cura = 0 (mago morto não pode curar)

SENÃO:
    ├─ SE alvo.morto == true E alvo.revivido == false:
    │  ├─ alvo.revive()  (morto=false, revivido=true)
    │  ├─ alvo.recebeCura(cura)
    │  ├─ mago.evoluir()
    │  └─ historico.add(alvo)
    │
    ├─ SENÃO SE alvo.morto == true E alvo.revivido == true:
    │  └─ Aplicar cura = 0 (não pode reviver novamente)
    │
    └─ SENÃO:
       └─ alvo.recebeCura(cura)  (cura normal)
```

**Regras de Ressurreição:**
- ✅ Pode reviver herói ATORDOADO (morto pela primeira vez)
- ❌ NÃO pode reviver herói MORTO (já foi revivido antes)
- 📈 Evolui ao ressuscitar (não ao curar)
- 📜 Adiciona ao histórico apenas ressurreições

#### Método toString

```java
@Override
public String toString()
```

**Saída:**
```
[super] | Mago ( Vida: [X]% ; Cura: [Y] )
```

---

### 5. Classe de Controle: `App`

**Localização:** `src/control/App.java`

**Responsabilidade:** Interface de usuário e controle do fluxo do jogo.

#### Estrutura de Dados Principal

```java
ArrayList<Heroi> arena = new ArrayList<Heroi>();
```

**Descrição:** Armazena todos os heróis criados durante a sessão.

#### Método Main

```java
public static void main(String[] args)
```

**Loop Infinito:**
```java
for (int i = 1; i != 0; i++) {
    // Exibe menu
    // Lê opção
    // Executa ação
}
```

#### Menu Principal

```
+----------------------------------------+
|     Bem vindo ao Clash of Realms       |
+----------------------------------------+
|      (0) Sair                          |
|      (1) Crie seu Guerreiro            |
|      (2) Crie seu Arqueiro             |
|      (3) Crie seu Mago                 |
|      (4) Retirar Herói                 |
|      (5) Mostrar Heróis                |
|      (6) Ordene os Heróis              |
|      (7) Aniversario de um Herói       |
|      (8) Usar habilidade de um Herói   |
+----------------------------------------+
```

#### Opções Detalhadas

##### Opção 0: Sair

**Fluxo:**
```
Usuário escolhe 0
    ↓
Exibe confirmação
    ↓
Lê confirmação (0=Sair, 1=Cancelar)
    ↓
SE 0: break (encerra programa)
SE 1: continue (volta ao menu)
```

**Aviso Importante:**
```
AVISO: Jogo sem salvamento automático.
Caso saia, todo seu progresso será PERDIDO!!!!
```

##### Opções 1, 2, 3: Criar Herói

**Entrada de Dados:**
```java
System.out.println("Nome do Heroi : ");
String nome = teclado.nextLine();

System.out.println("Idade do Heroi : ");
int idade = Integer.parseInt(teclado.nextLine());

System.out.println("Altura do Heroi : ");
double altura = Double.parseDouble(teclado.nextLine());
```

**Criação:**
```java
// Opção 1
arena.add(new Guerreiro(nome, idade, altura));

// Opção 2
arena.add(new Arqueiro(nome, idade, altura));

// Opção 3
arena.add(new Mago(nome, idade, altura));
```

##### Opção 4: Retirar Herói

**Fluxo:**
```
Solicita ID do herói
    ↓
Exibe informações do herói removido
    ↓
SE Guerreiro OU Arqueiro:
    └─ Exibe lista de "Abates" (histórico)
    
SE Mago:
    └─ Exibe lista de "Heróis Salvos" (histórico)
    ↓
Remove da arena
```

**Código:**
```java
System.out.println("Herói removido: " + arena.get(id).toString());

if (arena.get(id) instanceof Guerreiro || 
    arena.get(id) instanceof Arqueiro) {
    System.out.println("\nAbates:");
    for (Heroi par : arena.get(id).getHistorico()) {
        System.out.println(par.toString());
    }
}

if (arena.get(id) instanceof Mago) {
    System.out.println("\nHeróis Salvos:");
    for (Heroi par : arena.get(id).getHistorico()) {
        System.out.println(par.toString());
    }
}

arena.remove(id);
```

##### Opção 5: Mostrar Heróis

**Saída:**
```
Heróis:

0 - João (VIVO) | Nível: 3 | 25 Anos e 1.75m | Guerreiro (...)
1 - Maria (VIVO) | Nível: 5 | 30 Anos e 1.65m | Mago (...)
2 - Pedro (ATORDOADO) | Nível: 2 | 22 Anos e 1.80m | Arqueiro (...)
```

##### Opção 6: Ordenar Heróis

**Algoritmo:**
```java
Collections.sort(arena);
```

Utiliza o método `compareTo()` implementado em `Heroi`.

**Critérios:**
1. Idade (menor → maior)
2. Altura (menor → maior)
3. Vida (menor → maior)

##### Opção 7: Aniversário

**Código:**
```java
int aniversariante = Integer.parseInt(teclado.nextLine());

if (aniversariante == 0 || aniversariante <= arena.size() - 1) {
    arena.get(aniversariante).aniversario();
} else {
    System.out.println("O Herói informado é inexistente");
}
```

**Efeito:** Incrementa idade do herói em 1 ano.

##### Opção 8: Usar Habilidade

**Fluxo de Execução:**
```
Solicita ID do atacante (p1)
    ↓
Valida ID
    ↓
Solicita ID do alvo (p2)
    ↓
Valida ID
    ↓
Executa: arena.get(p1).habilidade(arena.get(p2))
```

**Validação:**
```java
if (p1 == 0 || p1 <= arena.size() - 1) {
    if (p2 == 0 || p2 <= arena.size() - 1) {
        arena.get(p1).habilidade(arena.get(p2));
    } else {
        System.out.println("O Herói informado é inexistente");
    }
} else {
    System.out.println("O Herói informado é inexistente");
}
```

---

## 🔄 Fluxogramas

### Fluxograma: Receber Dano

```
           ┌─────────────────┐
           │  recebeDano(val)│
           └────────┬─────────┘
                    │
                    ▼
           ┌─────────────────┐
           │   i = val       │
           └────────┬─────────┘
                    │
           ┌────────▼─────────┐
      ┌────┤   i != 0?        ├────┐
      │NÃO └──────────────────┘ SIM│
      │                            │
      │                            ▼
      │                   ┌─────────────────┐
      │                   │ vida - i < 0?   │
      │                   └────┬────────┬───┘
      │                        │SIM     │NÃO
      │                        │        │
      │                        │        ▼
      │                        │  ┌──────────┐
      │                        │  │vida -= i │
      │                        │  └────┬─────┘
      │                        │       │
      │                        │       ▼
      │                        │  ┌──────────┐
      │                        │  │  break   │
      │                        │  └────┬─────┘
      │                        │       │
      │                        ▼       │
      │                   ┌──────────┐ │
      │                   │   i--    │ │
      │                   └────┬─────┘ │
      │                        │       │
      │                        └───────┘
      │
      ▼
  ┌────────┐
  │  FIM   │
  └────────┘
```

### Fluxograma: Habilidade do Guerreiro

```
                    ┌─────────────────┐
                    │ habilidade(par) │
                    └────────┬─────────┘
                             │
                ┌────────────▼────────────┐
         ┌──────┤  this.morto == true?    ├──────┐
         │SIM   └─────────────────────────┘   NÃO│
         │                                       │
         ▼                                       ▼
    ┌─────────┐                    ┌─────────────────────┐
    │ Dano=0  │                ┌───┤  par == this?       ├───┐
    │ Return  │                │SIM└─────────────────────┘NÃO│
    └─────────┘                │                             │
                               ▼                             ▼
                          ┌─────────┐           ┌─────────────────────┐
                          │ Dano=0  │       ┌───┤ par.vida == 0?      ├───┐
                          │ Return  │       │SIM└─────────────────────┘NÃO│
                          └─────────┘       │                             │
                                            ▼                             ▼
                                       ┌─────────┐         ┌──────────────────────┐
                                       │ Dano=0  │         │ par.vida - dano <= 0?│
                                       │ Return  │         └──────┬────────┬──────┘
                                       └─────────┘              SIM│        │NÃO
                                                                   │        │
                                              ┌────────────────────┘        └──────┐
                                              │                                    │
                                              ▼                                    ▼
                                  ┌───────────────────────┐         ┌──────────────────┐
                                  │ recebeDano(par.vida)  │         │ recebeDano(dano) │
                                  │ par.morre()           │         └────────┬─────────┘
                                  │ this.evoluir()        │                  │
                                  │ historico.add(par)    │                  ▼
                                  └───────────┬───────────┘         ┌──────────────────┐
                                              │                     │ SE dano+1 <= 70: │
                                              │                     │   dano++         │
                                              │                     └────────┬─────────┘
                                              │                              │
                                              └──────────────┬───────────────┘
                                                             │
                                                             ▼
                                                        ┌────────┐
                                                        │  FIM   │
                                                        └────────┘
```

---

## ⚔️ Mecânicas do Jogo

### Sistema de Vida e Morte

#### Estados do Herói

| Estado | Condição | Descrição |
|--------|----------|-----------|
| **VIVO** | `morto=false` | Pode atacar e receber habilidades |
| **ATORDOADO** | `morto=true`<br>`revivido=false` | Primeira morte, pode ser revivido |
| **MORTO** | `morto=true`<br>`revivido=true` | Morte permanente, sem revivir |

#### Transições de Estado

```
VIVO ──[vida=0]──> ATORDOADO ──[Mago.revive()]──> VIVO (revivido=true)
  ▲                                                   │
  │                                                   │
  └────────────────[vida=0]──────────────────────────┘
                                                      │
                                                      ▼
                                                    MORTO
```

### Sistema de Combate

#### Comparação de Classes

| Aspecto | Guerreiro | Arqueiro | Mago |
|---------|-----------|----------|------|
| **Função** | Dano direto | Dano múltiplo | Suporte |
| **Dano Inicial** | 30 fixo | 0-40 aleatório | N/A |
| **Dano Máximo** | 70 | 50 | N/A |
| **Cura Inicial** | N/A | N/A | 20 |
| **Cura Máxima** | N/A | N/A | 50 |
| **Ataques/Turno** | 1 | 1-3+ | N/A |
| **Revive** | ❌ | ❌ | ✅ |
| **Evolui ao:** | Matar | Matar | Reviver |
| **Progressão** | +1~5 dano | +1 dano, +flechas | +5 cura |

#### Vantagens e Desvantagens

**Guerreiro:**
- ✅ Dano previsível e consistente
- ✅ Forte contra alvos isolados
- ✅ Escala bem no late game (dano máximo 70)
- ❌ Um ataque por turno
- ❌ Sem suporte

**Arqueiro:**
- ✅ Potencial de burst damage (3 flechas)
- ✅ Evolução balanceada (dano + flechas)
- ✅ Pode matar antes de todas as flechas
- ❌ Dano aleatório (RNG)
- ❌ Dano máximo menor (50)
- ❌ Flechas desperdiçadas após kill

**Mago:**
- ✅ Único que pode reviver aliados
- ✅ Suporte ilimitado (sem limite de curas)
- ✅ Evolução rápida (+5 cura)
- ❌ Sem dano ofensivo
- ❌ Dependente de aliados
- ❌ Inútil se for o último sobrevivente

### Sistema de Progressão

#### Evolução de Dano/Cura

**Guerreiro:**
```
Nível  1:  30 dano
Nível  2:  35 dano (+5)
Nível  3:  40 dano (+5)
...
Nível  9:  70 dano (MÁXIMO)
Nível 10+: 70 dano (sem mudança)
```

**Arqueiro:**
```
Nível  1: 1 flecha, 40 dano
Nível  2: 1 flecha, 40 dano
Nível  3: 2 flechas, 41 dano ⭐
Nível  5: 3 flechas, 42 dano ⭐
Nível  7: 3 flechas, 43 dano
...
Nível 21+: 3 flechas, 50 dano (MÁXIMO)
```

**Mago:**
```
Nível 1: 20 cura
Nível 2: 25 cura (+5)
Nível 3: 30 cura (+5)
...
Nível 7: 50 cura (MÁXIMO)
Nível 8+: 50 cura (sem mudança)
```

### Histórico de Ações

#### Para Guerreiros e Arqueiros

**Armazena:** Heróis que foram mortos

**Exibido como:** "Abates"

**Exemplo:**
```
Abates:
Pedro (MORTO) | Nível: 3 | 28 Anos e 1.78m | Arqueiro (...)
Ana (MORTO) | Nível: 2 | 24 Anos e 1.65m | Mago (...)
```

#### Para Magos

**Armazena:** Heróis que foram revividos

**Exibido como:** "Heróis Salvos"

**Exemplo:**
```
Heróis Salvos:
João (VIVO) | Nível: 5 | 30 Anos e 1.85m | Guerreiro (...)
Carlos (MORTO) | Nível: 4 | 26 Anos e 1.72m | Arqueiro (...)
```

---

## 📝 Guia de Uso

### Instalação e Execução

#### Requisitos

- Java JDK 20 ou superior
- NetBeans IDE 18+ (opcional)
- Sistema operacional: Windows, Linux ou macOS

#### Compilação via Terminal

```bash
# Navegar até o diretório
cd ClashOfRealms

# Compilar
javac -d build/classes src/entity/*.java src/control/*.java

# Executar
java -cp build/classes control.App
```

#### Compilação via NetBeans

1. Abrir NetBeans
2. File → Open Project → Selecionar `ClashOfRealms`
3. Clicar com botão direito no projeto → Build
4. Run → Run Project (ou F6)

### Exemplo de Sessão de Jogo

#### Criando Heróis

```
Bem vindo ao Clash of Realms
(1) Crie seu Guerreiro

Nome do Heroi: Thor
Idade do Heroi: 1500
ERRO: A idade máxima permitida é 122 Anos

Idade do Heroi: 35
Altura do Heroi: 1.95

✓ Guerreiro criado!

(2) Crie seu Arqueiro
Nome do Heroi: Legolas
Idade do Heroi: 2931
ERRO: A idade máxima permitida é 122 Anos

Idade do Heroi: 87
Altura do Heroi: 1.85

✓ Arqueiro criado!

(3) Crie seu Mago
Nome do Heroi: Gandalf
Idade do Heroi: 122
Altura do Heroi: 1.78

✓ Mago criado!
```

#### Listando Heróis

```
(5) Mostrar Heróis

Heróis:

0 - Thor (VIVO) | Nível: 1 | 35 Anos e 1.95m | Guerreiro ( Vida: 100% ; Dano: 30 )
1 - Legolas (VIVO) | Nível: 1 | 87 Anos e 1.85m | Arqueiro ( Vida: 100% ; Dano Máximo: 40 ; Aljava: 1 flecha(s) )
2 - Gandalf (VIVO) | Nível: 1 | 122 Anos e 1.78m | Mago ( Vida: 100% ; Cura: 20 )
```

#### Combate

```
(8) Usar habilidade de um Herói

Qual Herói utilizará a habilidade? (ID): 0
Qual será afetado pela habilidade? (ID): 1

→ Thor ataca Legolas!
→ Legolas recebe 30 de dano!

(5) Mostrar Heróis

0 - Thor (VIVO) | Nível: 1 | 35 Anos e 1.95m | Guerreiro ( Vida: 100% ; Dano: 31 )
1 - Legolas (VIVO) | Nível: 1 | 87 Anos e 1.85m | Arqueiro ( Vida: 70% ; Dano Máximo: 40 ; Aljava: 1 flecha(s) )
2 - Gandalf (VIVO) | Nível: 1 | 122 Anos e 1.78m | Mago ( Vida: 100% ; Cura: 20 )

(8) Usar habilidade

Qual Herói utilizará a habilidade? (ID): 1
Qual será afetado pela habilidade? (ID): 0

→ Legolas ataca Thor!
→ Dano: 28 (aleatório entre 0-40)

0 - Thor (VIVO) | Nível: 1 | 35 Anos e 1.95m | Guerreiro ( Vida: 72% ; Dano: 31 )
1 - Legolas (VIVO) | Nível: 1 | 87 Anos e 1.85m | Arqueiro ( Vida: 70% ; Dano Máximo: 40 ; Aljava: 1 flecha(s) )
```

#### Cura e Ressurreição

```
(8) Usar habilidade

Qual Herói utilizará a habilidade? (ID): 2
Qual será afetado pela habilidade? (ID): 1

→ Gandalf cura Legolas!
→ Legolas recupera 20 de vida!

1 - Legolas (VIVO) | Nível: 1 | 87 Anos e 1.85m | Arqueiro ( Vida: 90% ; Dano Máximo: 40 ; Aljava: 1 flecha(s) )

// Depois de Legolas morrer...

1 - Legolas (ATORDOADO) | Nível: 2 | 87 Anos e 1.85m | Arqueiro ( Vida: 0% ; Dano Máximo: 40 ; Aljava: 1 flecha(s) )

(8) Usar habilidade

Qual Herói utilizará a habilidade? (ID): 2
Qual será afetado pela habilidade? (ID): 1

→ Gandalf revive Legolas!
→ Legolas retorna à vida!
→ Gandalf evoluiu para nível 2!

0 - Thor (VIVO) | Nível: 3 | 35 Anos e 1.95m | Guerreiro ( Vida: 55% ; Dano: 41 )
1 - Legolas (VIVO) | Nível: 2 | 87 Anos e 1.85m | Arqueiro ( Vida: 20% ; Dano Máximo: 40 ; Aljava: 1 flecha(s) )
2 - Gandalf (VIVO) | Nível: 2 | 122 Anos e 1.78m | Mago ( Vida: 100% ; Cura: 25 )
```

#### Ordenação

```
(6) Ordene os Heróis

Heróis Ordenados!!!

0 - Thor (VIVO) | Nível: 3 | 35 Anos e 1.95m | ...
1 - Legolas (VIVO) | Nível: 2 | 87 Anos e 1.85m | ...
2 - Gandalf (VIVO) | Nível: 2 | 122 Anos e 1.78m | ...

(Ordenado por: Idade → Altura → Vida)
```

#### Removendo Herói

```
(4) Retirar Herói

Qual Herói deseja retirar? (ID): 0

Herói removido da Arena:
Thor (VIVO) | Nível: 3 | 35 Anos e 1.95m | Guerreiro ( Vida: 55% ; Dano: 41 )

Abates:
Legolas (MORTO) | Nível: 2 | 87 Anos e 1.85m | Arqueiro ( Vida: 0% ; Dano Máximo: 40 ; Aljava: 1 flecha(s) )
```

---

## 💻 Exemplos de Código

### Criando e Testando Heróis

```java
// Criar heróis
Guerreiro guerreiro = new Guerreiro("Arthas", 28, 1.85);
Arqueiro arqueiro = new Arqueiro("Sylvanas", 25, 1.70);
Mago mago = new Mago("Jaina", 30, 1.65);

// Combate
guerreiro.habilidade(arqueiro);  // Ataca arqueiro
System.out.println(arqueiro);    // Mostra vida restante

arqueiro.habilidade(guerreiro);  // Contra-ataque
System.out.println(guerreiro);

// Cura
mago.habilidade(guerreiro);      // Cura guerreiro
System.out.println(guerreiro);
```

### Evolução de Heróis

```java
Guerreiro g = new Guerreiro("Tank", 25, 1.80);
System.out.println("Nível inicial: " + g.getNivel());  // 1

g.evoluir();
System.out.println("Após evolução: " + g.getNivel()); // 2
System.out.println(g);  // Mostra dano aumentado
```

### Sistema de Ressurreição

```java
Arqueiro a = new Arqueiro("Ranger", 22, 1.75);
Guerreiro g = new Guerreiro("Knight", 30, 1.90);
Mago m = new Mago("Healer", 28, 1.68);

// Matar arqueiro
g.habilidade(a);  // Ataque 1
g.habilidade(a);  // Ataque 2
g.habilidade(a);  // Ataque 3 - mata

System.out.println(a.getMorto());      // true
System.out.println(a.getRevivido());   // false
System.out.println("Estado: ATORDOADO");

// Reviver
m.habilidade(a);
System.out.println(a.getMorto());      // false
System.out.println(a.getRevivido());   // true
System.out.println("Estado: VIVO (revivido)");

// Matar novamente
g.habilidade(a);
g.habilidade(a);
g.habilidade(a);

System.out.println(a.getMorto());      // true
System.out.println(a.getRevivido());   // true
System.out.println("Estado: MORTO PERMANENTE");

// Tentar reviver novamente
m.habilidade(a);
System.out.println(a.getMorto());      // true (continua morto)
```

### Ordenação com Comparable

```java
ArrayList<Heroi> lista = new ArrayList<>();

lista.add(new Guerreiro("Beta", 30, 1.80));
lista.add(new Arqueiro("Alpha", 25, 1.75));
lista.add(new Mago("Gamma", 25, 1.80));

System.out.println("Antes da ordenação:");
for (Heroi h : lista) {
    System.out.println(h);
}

Collections.sort(lista);

System.out.println("\nApós ordenação:");
for (Heroi h : lista) {
    System.out.println(h);
}

/* Saída:
Alpha (VIVO) | 25 Anos e 1.75m ...  (menor idade, menor altura)
Gamma (VIVO) | 25 Anos e 1.80m ...  (mesma idade, maior altura)
Beta (VIVO) | 30 Anos e 1.80m ...   (maior idade)
*/
```

### Validações do Construtor

```java
// Tentando criar herói com dados inválidos

try {
    Guerreiro g1 = new Guerreiro("", 25, 1.80);
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());
    // "Nome deve ser preenchido"
}

try {
    Guerreiro g2 = new Guerreiro("Hero", -5, 1.80);
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());
    // "Idade nao pode ser negativa"
}

try {
    Guerreiro g3 = new Guerreiro("Hero", 150, 1.80);
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());
    // "A idade máxima permitida é 122 Anos..."
}

try {
    Guerreiro g4 = new Guerreiro("Hero", 25, 3.00);
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());
    // "Altura máxima permitida é de 2.74m..."
}

try {
    Guerreiro g5 = new Guerreiro(null, 25, 1.80);
} catch (NullPointerException e) {
    System.out.println(e.getMessage());
    // "nome nao pode ser nulo"
}
```

---

## 📁 Estrutura de Diretórios

```
ClashOfRealms/
│
├── 📄 README.md                    # Descrição básica do projeto
├── 📄 build.xml                    # Script de build Ant
├── 📄 manifest.mf                  # Manifesto JAR
│
├── 📂 src/                         # Código-fonte
│   ├── 📂 entity/                  # Entidades do jogo
│   │   ├── 📄 Heroi.java           # Classe abstrata base
│   │   ├── 📄 Guerreiro.java       # Classe Guerreiro
│   │   ├── 📄 Arqueiro.java        # Classe Arqueiro
│   │   └── 📄 Mago.java            # Classe Mago
│   │
│   └── 📂 control/                 # Controle da aplicação
│       └── 📄 App.java             # Classe principal (Main)
│
├── 📂 nbproject/                   # Configurações NetBeans
│   ├── 📄 build-impl.xml           # Build implementation
│   ├── 📄 genfiles.properties      # Arquivos gerados
│   ├── 📄 project.properties       # Propriedades do projeto
│   ├── 📄 project.xml              # Definição do projeto
│   │
│   └── 📂 private/                 # Configurações privadas
│       └── 📄 private.properties   # Propriedades do usuário
│
├── 📂 build/                       # Arquivos compilados (gerado)
│   ├── 📂 classes/                 # .class files
│   └── 📂 test/                    # Testes compilados
│
└── 📂 dist/                        # Distribuição (gerado)
    └── 📄 ClashOfRealms.jar        # JAR executável
```

### Descrição dos Diretórios

| Diretório | Conteúdo | Gerado? |
|-----------|----------|---------|
| `src/` | Código-fonte Java | ❌ Manual |
| `build/` | Arquivos compilados (.class) | ✅ Automático |
| `dist/` | JAR executável | ✅ Automático |
| `nbproject/` | Configurações do NetBeans | ❌ IDE |
| `test/` | Testes unitários | ❌ Manual |

---

## 🎓 Conceitos de POO Aplicados

### 1. Abstração

**Exemplo:** Classe `Heroi`

A classe abstrata `Heroi` define o conceito abstrato de um herói, sem implementar comportamentos específicos como `habilidade()` e `evoluir()`.

```java
public abstract class Heroi {
    abstract public void habilidade(Heroi par);
    abstract public void evoluir();
}
```

### 2. Encapsulamento

**Exemplo:** Atributos privados com getters

```java
private int vida;
private final String nome;

public int getVida() { return this.vida; }
public String getNome() { return this.nome; }
```

**Benefícios:**
- Protege dados internos
- Controla acesso aos atributos
- Permite validações nos setters (se existissem)

### 3. Herança

**Exemplo:** Hierarquia de classes

```java
Heroi  ← [extends] ← Guerreiro
       ← [extends] ← Arqueiro
       ← [extends] ← Mago
```

**Reutilização de código:**
- Todos herdam `recebeDano()`, `recebeCura()`, `compareTo()`
- Não precisam reimplementar lógica comum

### 4. Polimorfismo

**Exemplo:** Sobrescrita de métodos

```java
// Classe base
public abstract void habilidade(Heroi par);

// Guerreiro
@Override
public void habilidade(Heroi par) {
    // Implementação de ataque
}

// Mago
@Override
public void habilidade(Heroi par) {
    // Implementação de cura
}
```

**Uso polimórfico:**
```java
ArrayList<Heroi> arena = new ArrayList<>();
arena.add(new Guerreiro(...));
arena.add(new Mago(...));

// Mesmo método, comportamentos diferentes
arena.get(0).habilidade(arena.get(1));  // Ataca
arena.get(1).habilidade(arena.get(0));  // Cura
```

### 5. Interface

**Exemplo:** `Comparable<T>`

```java
public abstract class Heroi implements Comparable {
    @Override
    public int compareTo(Object par) {
        // Implementação personalizada
    }
}
```

**Benefício:**
- Permite uso de `Collections.sort()`
- Contrato de ordenação

### 6. Composição

**Exemplo:** `ArrayList<Heroi>`

```java
private final ArrayList<Heroi> historico;
```

**Relacionamento:**
- `Heroi` **TEM-UM** histórico de heróis
- Composição: histórico é parte do herói
- Lifecycle: histórico é criado e destruído com o herói

### 7. Agregação

**Exemplo:** Arena

```java
ArrayList<Heroi> arena = new ArrayList<Heroi>();
```

**Relacionamento:**
- Arena **TEM-MUITOS** heróis
- Agregação: heróis podem existir independentemente
- Lifecycle: remover da arena não destroi o herói

---

## 🐛 Tratamento de Erros

### Validações Implementadas

#### No Construtor de Heroi

| Validação | Tipo de Exceção | Mensagem |
|-----------|-----------------|----------|
| Nome nulo | `NullPointerException` | "nome nao pode ser nulo" |
| Nome vazio | `IllegalArgumentException` | "Nome deve ser preenchido" |
| Idade < 0 | `IllegalArgumentException` | "Idade nao pode ser negativa" |
| Idade > 122 | `IllegalArgumentException` | "A idade máxima permitida é 122 Anos..." |
| Altura < 0 | `IllegalArgumentException` | "altura nao pode ser negativo" |
| Altura > 2.74 | `IllegalArgumentException` | "Altura máxima permitida é de 2.74m..." |

#### Em App.java

```java
// Validação de opção inválida
if (opcao < 0 || opcao > 8) {
    System.out.println("Número Inválido");
}

// Validação de ID de herói
if (aniversariante == 0 || aniversariante <= arena.size() - 1) {
    // OK
} else {
    System.out.println("O Herói informado é inexistente");
}
```

### Melhorias Sugeridas

1. **Try-Catch para parseInt()**
```java
try {
    int opcao = Integer.parseInt(opcaoTexto);
} catch (NumberFormatException e) {
    System.out.println("Por favor, digite um número válido");
    continue;
}
```

2. **Validação de Arena Vazia**
```java
if (arena.isEmpty()) {
    System.out.println("Crie heróis antes de usar esta opção!");
    continue;
}
```

3. **Confirmação de Ações Críticas**
```java
System.out.println("Tem certeza? Esta ação não pode ser desfeita!");
```

---

## 🔧 Configurações do Projeto

### NetBeans Project Properties

**Versão do Java:** 20

```properties
javac.source=20
javac.target=20
```

**Classe Principal:**
```properties
main.class=control.App
```

**Encoding:**
```properties
source.encoding=UTF-8
```

### Build Configuration

**Ant Build File:** `build.xml`

**Targets Principais:**
- `clean` - Remove arquivos compilados
- `compile` - Compila código-fonte
- `jar` - Cria arquivo JAR
- `run` - Executa aplicação
- `test` - Executa testes

### Geração de JAR

```xml
<target name="jar" depends="compile">
    <jar destfile="dist/ClashOfRealms.jar" basedir="build/classes">
        <manifest>
            <attribute name="Main-Class" value="control.App"/>
        </manifest>
    </jar>
</target>
```

---

## 📊 Tabelas de Referência Rápida

### Comandos do Menu

| Opção | Comando | Descrição |
|-------|---------|-----------|
| 0 | Sair | Encerra o programa |
| 1 | Criar Guerreiro | Adiciona novo Guerreiro |
| 2 | Criar Arqueiro | Adiciona novo Arqueiro |
| 3 | Criar Mago | Adiciona novo Mago |
| 4 | Retirar Herói | Remove herói da arena |
| 5 | Mostrar Heróis | Lista todos os heróis |
| 6 | Ordenar Heróis | Ordena por idade/altura/vida |
| 7 | Aniversário | Incrementa idade |
| 8 | Usar Habilidade | Ataca/cura outro herói |

### Estatísticas Base

| Classe | Vida | Atributo Especial | Valor Inicial | Incremento | Máximo |
|--------|------|-------------------|---------------|------------|--------|
| Todos | 100% | - | - | - | 100% |
| Guerreiro | - | Dano | 30 | 1-5 | 70 |
| Arqueiro | - | Dano | 40 | 1 | 50 |
| Arqueiro | - | Flechas | 1 | 1 | ∞ |
| Mago | - | Cura | 20 | 5 | 50 |

### Limites de Criação

| Atributo | Mínimo | Máximo | Razão |
|----------|--------|--------|-------|
| Idade | 0 | 122 | Jeanne Calment (pessoa mais velha) |
| Altura | 0 | 2.74m | Robert Wadlow (pessoa mais alta) |
| Nome | 1 char | ∞ | Não pode ser vazio |

---

## 🎯 Casos de Uso

### UC-01: Criar Herói

**Ator:** Jogador

**Pré-condição:** Aplicação está em execução

**Fluxo Principal:**
1. Jogador seleciona opção 1, 2 ou 3
2. Sistema solicita nome
3. Jogador informa nome
4. Sistema solicita idade
5. Jogador informa idade
6. Sistema solicita altura
7. Jogador informa altura
8. Sistema cria herói e adiciona à arena
9. Sistema exibe confirmação

**Fluxo Alternativo - Validação:**
- 3a. Nome vazio → Exibe erro, retorna ao menu
- 5a. Idade inválida → Exibe erro, retorna ao menu
- 7a. Altura inválida → Exibe erro, retorna ao menu

### UC-02: Combate

**Ator:** Jogador

**Pré-condição:** Pelo menos 2 heróis na arena

**Fluxo Principal:**
1. Jogador seleciona opção 8
2. Sistema solicita ID do atacante
3. Jogador informa ID
4. Sistema valida ID
5. Sistema solicita ID do alvo
6. Jogador informa ID
7. Sistema valida ID
8. Sistema executa habilidade
9. Sistema atualiza estado dos heróis

**Regras de Negócio:**
- RN01: Herói morto não pode atacar
- RN02: Não pode atacar a si mesmo
- RN03: Alvo já morto não recebe dano
- RN04: Matar inimigo evolui atacante

### UC-03: Reviver Herói

**Ator:** Jogador (usando Mago)

**Pré-condição:** 
- Mago vivo na arena
- Herói atordoado na arena

**Fluxo Principal:**
1. Jogador seleciona opção 8
2. Informa ID do Mago
3. Informa ID do herói atordoado
4. Sistema verifica condições
5. Sistema revive herói
6. Sistema aplica cura inicial
7. Sistema evolui Mago
8. Sistema adiciona ao histórico

**Regras de Negócio:**
- RN05: Só pode reviver heróis ATORDOADOS
- RN06: Herói MORTO não pode ser revivido
- RN07: Mago evolui ao reviver (não ao curar)

---

## 🚀 Melhorias Futuras

### Funcionalidades Sugeridas

1. **Sistema de Salvamento**
   - Serialização de objetos
   - Arquivos JSON/XML
   - Banco de dados SQLite

2. **Interface Gráfica**
   - JavaFX ou Swing
   - Sprites dos heróis
   - Animações de combate

3. **Sistema de Itens**
   - Armas, armaduras, poções
   - Inventário por herói
   - Equipamentos com bônus

4. **Expansão de Classes**
   - Paladino (tanque + cura)
   - Assassino (dano crítico)
   - Necromante (revive inimigos)

5. **Sistema de Experiência**
   - XP ao invés de evolução imediata
   - Curva de progressão balanceada
   - Skills desbloqueáveis

6. **Multiplayer**
   - PvP via rede
   - Sistema de turnos sincronizado
   - Ranking global

7. **IA para NPCs**
   - Inimigos controlados por IA
   - Dificuldade progressiva
   - Padrões de ataque inteligentes

### Refatorações Técnicas

1. **Padrão Strategy**
```java
interface EstrategiaAtaque {
    void atacar(Heroi alvo);
}

class Guerreiro extends Heroi {
    private EstrategiaAtaque estrategia;
}
```

2. **Padrão Observer**
```java
interface HeroiObserver {
    void onMorte(Heroi heroi);
    void onEvoluir(Heroi heroi);
}
```

3. **Padrão Factory**
```java
class HeroiFactory {
    public static Heroi criar(TipoHeroi tipo, String nome, ...) {
        switch(tipo) {
            case GUERREIRO: return new Guerreiro(...);
            // ...
        }
    }
}
```

---

## 📚 Referências

### Conceitos de POO

- **Abstração:** Ocultar complexidade, mostrar apenas o essencial
- **Encapsulamento:** Proteger dados internos com modificadores de acesso
- **Herança:** Reutilização de código através de hierarquias
- **Polimorfismo:** Um método, múltiplos comportamentos

### Java Collections

- `ArrayList<T>` - Lista dinâmica
- `Collections.sort()` - Ordenação
- `Comparable<T>` - Interface de comparação

### Boas Práticas

✅ **Implementadas:**
- Atributos `private`
- Getters públicos
- Construtores com validação
- Métodos `@Override`
- Nomes descritivos

⚠️ **Não implementadas (mas recomendadas):**
- Javadoc em todos os métodos
- Testes unitários (JUnit)
- Logging (SLF4J, Log4j)
- Tratamento robusto de exceções

---

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais, demonstrando conceitos de Programação Orientada a Objetos em Java.

---

## ✍️ Autor

**Projeto:** Clash of Realms  
**Linguagem:** Java 20  
**IDE:** NetBeans 18  
**Objetivo:** Demonstração educacional de POO

---

<div align="center">

**📚 Documentação Completa - Clash of Realms**

*Desenvolvido com foco em conceitos de POO*

</div>
