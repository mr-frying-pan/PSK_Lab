package lt.vu.mybatis.model;

public class Weapon {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.weapons.id
     *
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.weapons.name
     *
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.weapons.power
     *
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    private Integer power;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.weapons.fighterid
     *
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    private Integer fighterid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.weapons.id
     *
     * @return the value of public.weapons.id
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.weapons.id
     *
     * @param id the value for public.weapons.id
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.weapons.name
     *
     * @return the value of public.weapons.name
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.weapons.name
     *
     * @param name the value for public.weapons.name
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.weapons.power
     *
     * @return the value of public.weapons.power
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    public Integer getPower() {
        return power;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.weapons.power
     *
     * @param power the value for public.weapons.power
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    public void setPower(Integer power) {
        this.power = power;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.weapons.fighterid
     *
     * @return the value of public.weapons.fighterid
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    public Integer getFighterid() {
        return fighterid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.weapons.fighterid
     *
     * @param fighterid the value for public.weapons.fighterid
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    public void setFighterid(Integer fighterid) {
        this.fighterid = fighterid;
    }
}